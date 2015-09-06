package cn.zy.apps.tools.web ;

import java.util.Collection ;
import java.util.Collections ;
import java.util.Comparator ;
import java.util.HashSet ;
import java.util.Map ;
import java.util.Set ;
import java.util.TreeMap ;
import java.util.regex.Matcher ;
import java.util.regex.Pattern ;

import com.opensymphony.xwork2.ActionContext ;
import com.opensymphony.xwork2.ActionInvocation ;
import com.opensymphony.xwork2.ValidationAware ;
import com.opensymphony.xwork2.inject.Inject ;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor ;
import com.opensymphony.xwork2.interceptor.NoParameters ;
import com.opensymphony.xwork2.interceptor.ParameterNameAware ;
import com.opensymphony.xwork2.util.ClearableValueStack ;
import com.opensymphony.xwork2.util.LocalizedTextUtil ;
import com.opensymphony.xwork2.util.MemberAccessValueStack ;
import com.opensymphony.xwork2.util.TextParseUtil ;
import com.opensymphony.xwork2.util.ValueStack ;
import com.opensymphony.xwork2.util.ValueStackFactory ;
import com.opensymphony.xwork2.util.logging.Logger ;
import com.opensymphony.xwork2.util.logging.LoggerFactory ;
import com.opensymphony.xwork2.util.reflection.ReflectionContextState ;

public class AutoParametersInterceptor extends MethodFilterInterceptor {

    /**
     * 
     */
    private static final long serialVersionUID = -4363846335381824485L ;

    private static final Logger LOG = LoggerFactory.getLogger(AutoParametersInterceptor.class) ;

    boolean ordered = true ;

    Set<Pattern> acceptParamses = Collections.emptySet() ;

    static boolean devMode = false ;

    private ValueStackFactory valueStackFactory ;

    @Inject
    public void setValueStackFactory(ValueStackFactory valueStackFactory) {
        this.valueStackFactory = valueStackFactory ;
    }

    @Inject("devMode")
    public static void setDevMode(String mode) {
        devMode = "true".equals(mode) ;
    }

    public void setAcceptParamNames(String commaDelim) {
        LOG.info("Accept Param  Names " + commaDelim) ;
        Collection<String> acceptPatterns = asCollection(commaDelim) ;
        if (acceptPatterns != null) {
            acceptParamses = new HashSet<Pattern>() ;
            for (String pattern : acceptPatterns) {
                acceptParamses.add(Pattern.compile(pattern.trim())) ;
            }
        }
    }

    /**
     * Compares based on number of '.' characters (fewer is higher)
     */
    static final Comparator<String> rbCollator = new Comparator<String>() {
        public int compare(String s1, String s2) {
            int l1 = 0, l2 = 0 ;
            for (int i = s1.length() - 1; i >= 0; i--) {
                if (s1.charAt(i) == '.') l1++ ;
            }
            for (int i = s2.length() - 1; i >= 0; i--) {
                if (s2.charAt(i) == '.') l2++ ;
            }
            return l1 < l2 ? -1 : (l2 < l1 ? 1 : s1.compareTo(s2)) ;
        }

    } ;

    @Override
    public String doIntercept(ActionInvocation invocation) throws Exception {
        Object action = invocation.getAction() ;
        if (!(action instanceof NoParameters)) {
            ActionContext ac = invocation.getInvocationContext() ;
            final Map<String, Object> parameters = retrieveParameters(ac) ;

            if (LOG.isDebugEnabled()) {
                LOG.debug("Setting params " + getParameterLogMap(parameters)) ;
            }

            if (parameters != null) {
                Map<String, Object> contextMap = ac.getContextMap() ;
                try {
                    ReflectionContextState.setCreatingNullObjects(contextMap, true) ;
                    ReflectionContextState.setDenyMethodExecution(contextMap, true) ;
                    ReflectionContextState.setReportingConversionErrors(contextMap, true) ;

                    ValueStack stack = ac.getValueStack() ;
                    setParameters(action, stack, parameters) ;
                } finally {
                    ReflectionContextState.setCreatingNullObjects(contextMap, false) ;
                    ReflectionContextState.setDenyMethodExecution(contextMap, false) ;
                    ReflectionContextState.setReportingConversionErrors(contextMap, false) ;
                }
            }
        }
        return invocation.invoke() ;
    }

    /**
     * Gets the parameter map to apply from wherever appropriate
     *
     * @param ac The action context
     * @return The parameter map to apply
     */
    protected Map<String, Object> retrieveParameters(ActionContext ac) {
        return ac.getParameters() ;
    }

    /**
     * Adds the parameters into context's ParameterMap
     *
     * @param ac        The action context
     * @param newParams The parameter map to apply
     *                  <p/>
     *                  In this class this is a no-op, since the parameters were fetched from the same location.
     *                  In subclasses both retrieveParameters() and addParametersToContext() should be overridden.
     */
    protected void addParametersToContext(ActionContext ac, Map<String, Object> newParams) {
    }

    protected void setParameters(Object action, ValueStack stack, final Map<String, Object> parameters) {
        ParameterNameAware parameterNameAware = (action instanceof ParameterNameAware) ? (ParameterNameAware) action : null ;

        Map<String, Object> acceptableParameters ;

        acceptableParameters = new TreeMap<String, Object>(getOrderedComparator()) ;

        for (Map.Entry<String, Object> entry : parameters.entrySet()) {
            String name = entry.getKey() ;

            boolean acceptableName = acceptableName(name) && (parameterNameAware == null || parameterNameAware.acceptableParameterName(name)) ;

            if (acceptableName) {

                acceptableParameters.put(name, entry.getValue()) ;
            }
        }

        ValueStack newStack = valueStackFactory.createValueStack(stack) ;
        boolean clearableStack = newStack instanceof ClearableValueStack ;
        if (clearableStack) {
            //if the stack's context can be cleared, do that to prevent OGNL
            //from having access to objects in the stack, see XW-641
            ((ClearableValueStack) newStack).clearContextValues() ;
            Map<String, Object> context = newStack.getContext() ;
            ReflectionContextState.setCreatingNullObjects(context, true) ;
            ReflectionContextState.setDenyMethodExecution(context, true) ;
            ReflectionContextState.setReportingConversionErrors(context, true) ;

            //keep locale from original context
            context.put(ActionContext.LOCALE, stack.getContext().get(ActionContext.LOCALE)) ;
        }

        boolean memberAccessStack = newStack instanceof MemberAccessValueStack ;
        if (memberAccessStack) {
            //block or allow access to properties
            //see WW-2761 for more details
            MemberAccessValueStack accessValueStack = (MemberAccessValueStack) newStack ;
            accessValueStack.setAcceptProperties(acceptParamses) ;
            //                    accessValueStack.setExcludeProperties(excludeParams) ;
        }

        String startHead = null ;

        ReDynaBean dynaBean1 = null ;// = new LazyDynaBean();

        for (Map.Entry<String, Object> entry : acceptableParameters.entrySet()) {

            String name = entry.getKey() ;

            Object value = entry.getValue() ;

            try {
                int index = name.indexOf(".") ;
                String startHead_ = name.substring(0, index) ;
                String startkey_ = name.substring(index + 1) ;

                if (startHead == null) {
                    startHead = startHead_ ;
                    dynaBean1 = new ReDynaBean() ;
                    dynaBean1.set(startkey_, value) ;
                    LOG.info("dynaBean  startHead  " + startHead + "  startkey_  " + startkey_ + "  value : " + value) ;
                } else if (!startHead.equals(startHead_)) {

                    newStack.setValue(startHead, dynaBean1) ;
                    dynaBean1 = new ReDynaBean() ;
                    startHead = startHead_ ;
                    dynaBean1.set(startkey_, value) ;
                    LOG.info("dynaBean  startHead  " + startHead + "  startkey_  " + startkey_ + "  value : " + value) ;

                } else {
                    dynaBean1.set(startkey_, value) ;
                    LOG.info("dynaBean  startHead  " + startHead + "  startkey_  " + startkey_ + "  value : " + value) ;
                }

            } catch (RuntimeException e) {
                if (devMode) {
                    String developerNotification = LocalizedTextUtil.findText(AutoParametersInterceptor.class, "devmode.notification", ActionContext.getContext().getLocale(), "Developer Notification:\n{0}", new Object[] { "Unexpected Exception caught setting '" + name + "' on '" + action.getClass() + ": " + e.getMessage() }) ;

                    LOG.error(developerNotification) ;
                    if (action instanceof ValidationAware) {
                        ((ValidationAware) action).addActionMessage(developerNotification) ;
                    }
                }
            }
        }
        if (dynaBean1 != null) {

            newStack.setValue(startHead, dynaBean1) ;
            //            System.out.println("==1> newStack startHead  : " + startHead + "  value : " +dynaBean1) ;
            if (clearableStack && (stack.getContext() != null) && (newStack.getContext() != null)) stack.getContext().put(ActionContext.CONVERSION_ERRORS, newStack.getContext().get(ActionContext.CONVERSION_ERRORS)) ;

            addParametersToContext(ActionContext.getContext(), acceptableParameters) ;
        }

    }

    /**
     * Gets an instance of the comparator to use for the ordered sorting.  Override this
     * method to customize the ordering of the parameters as they are set to the
     * action.
     *
     * @return A comparator to sort the parameters
     */
    protected Comparator<String> getOrderedComparator() {
        return rbCollator ;
    }

    private String getParameterLogMap(Map<String, Object> parameters) {
        if (parameters == null) {
            return "NONE" ;
        }

        StringBuilder logEntry = new StringBuilder() ;
        for (Map.Entry entry : parameters.entrySet()) {
            logEntry.append(String.valueOf(entry.getKey())) ;
            logEntry.append(" => ") ;
            if (entry.getValue() instanceof Object[]) {
                Object[] valueArray = (Object[]) entry.getValue() ;
                logEntry.append("[ ") ;
                if (valueArray.length > 0) {
                    for (int indexA = 0; indexA < (valueArray.length - 1); indexA++) {
                        Object valueAtIndex = valueArray[indexA] ;
                        logEntry.append(String.valueOf(valueAtIndex)) ;
                        logEntry.append(", ") ;
                    }
                    logEntry.append(String.valueOf(valueArray[valueArray.length - 1])) ;
                }
                logEntry.append(" ] ") ;
            } else {
                logEntry.append(String.valueOf(entry.getValue())) ;
            }
        }

        return logEntry.toString() ;
    }

    protected boolean acceptableName(String name) {
        if (isAccepted(name)) {
            return true ;
        }
        return false ;
    }

    protected boolean isAccepted(String paramName) {

        if (!this.acceptParamses.isEmpty()) {
            for (Pattern pattern : acceptParamses) {

                Matcher matcher = pattern.matcher(paramName) ;

                if (matcher.matches()) {
                    return true ;
                }
            }
            return false ;
        } else
            return false ;
    }

    /**
     * Whether to order the parameters or not
     *
     * @return True to order
     */
    public boolean isOrdered() {
        return ordered ;
    }

    /**
     * Set whether to order the parameters by object depth or not
     *
     * @param ordered True to order them
     */
    public void setOrdered(boolean ordered) {
        this.ordered = ordered ;
    }

    /**
     * Return a collection from the comma delimited String.
     *
     * @param commaDelim the comma delimited String.
     * @return A collection from the comma delimited String. Returns <tt>null</tt> if the string is empty.
     */
    private Collection<String> asCollection(String commaDelim) {
        if (commaDelim == null || commaDelim.trim().length() == 0) {
            return null ;
        }
        return TextParseUtil.commaDelimitedStringToSet(commaDelim) ;
    }

}
