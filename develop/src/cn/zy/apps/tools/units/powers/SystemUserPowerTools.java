package cn.zy.apps.tools.units.powers ;

import java.util.ArrayList ;

import java.util.HashMap ;
import java.util.Iterator ;
import java.util.List ;
import java.util.Map ;

import net.sf.json.JSONArray ;

public class SystemUserPowerTools {

    public static  <X>   List<TreeData>  switch2TreeData(List<X> userPowers) {
        List<TreeData> userPowerList = new ArrayList<TreeData>() ;
        for (Object up_ : userPowers) {
            
            UserPower<UserOptPower> up= (UserPower<UserOptPower>) up_;
            
            List<UserOptPower> systemUserOptPowers = up.getUserOptPowers() ;
            Map<String, Integer> map = new HashMap<String, Integer>() ;
            for (UserOptPower systemUserOptPower : systemUserOptPowers) {
                map.put(systemUserOptPower.getPowerName(), systemUserOptPower.getIsUse()) ;
            }
            JSONArray jsonArr = new JSONArray() ;
            jsonArr.add(map) ;
            userPowerList.add(new TreeData(up.getModuleId(), null, false, null, jsonArr.toString())) ;
        }
        return userPowerList ;
    }

    public static List<UserPower<UserOptPower>> paserUserPower(String powerStr) throws Exception {

        List<UserPower<UserOptPower>> userPowers = new ArrayList<UserPower<UserOptPower>>() ;
        powerStr = "[" + powerStr + "]" ;
        Map<String, List<PowerBean>> userPowerMap = SystemUserPowerTools.paserUserPower_(powerStr) ;

        for (Map.Entry<String, List<PowerBean>> entry : userPowerMap.entrySet()) {
            UserPower<UserOptPower> userPower = new UserPower<UserOptPower>() ;
            userPower.setModuleId(entry.getKey()) ;
            List<UserOptPower> systemUserOptPowers = new ArrayList<UserOptPower>() ;
            for (PowerBean powerBean : entry.getValue()) {
                UserOptPower systemUserOptPower = new UserOptPower() ;
                systemUserOptPower.setPowerName(powerBean.getName()) ;
                systemUserOptPower.setIsUse(powerBean.getValue()) ;
                systemUserOptPower.setPowerCode(powerBean.getOptName()) ;
                systemUserOptPowers.add(systemUserOptPower) ;
            }
            userPower.setUserOptPowers(systemUserOptPowers) ;
            userPowers.add(userPower) ;
        }

        return userPowers ;
    }

    private static Map<String, List<PowerBean>> paserUserPower_(String powerStr) throws Exception {
        Map<String, List<PowerBean>> modulePowerBean = new HashMap<String, List<PowerBean>>() ;

        JSONArray jsonArr = JSONArray.fromObject(powerStr) ;
        @SuppressWarnings("unchecked")
        Iterator<Map.Entry<String, Object>> iterator = ((Map<String, Object>) jsonArr.get(0)).entrySet().iterator() ;
        if (iterator.hasNext()) {
            Map.Entry<String, Object> children_entry = (Map.Entry<String, Object>) iterator.next() ;
            JSONArray value_arr = JSONArray.fromObject(children_entry.getValue()) ;
            decodeJson(value_arr, modulePowerBean) ;
        }
        return modulePowerBean ;

    }

    private static void decodeJson(JSONArray jsonArr, Map<String, List<PowerBean>> modulePowerBean) {
        for (int i = 0; i < jsonArr.size(); i++) {
            String treeId = null ;
            List<PowerBean> powerList = new ArrayList<PowerBean>() ;
            @SuppressWarnings("unchecked")
            Iterator<Map.Entry<String, Object>> iterator = ((Map<String, Object>) jsonArr.get(i)).entrySet().iterator() ;
            while (iterator.hasNext()) {
                Map.Entry<String, Object> children_entry = (Map.Entry<String, Object>) iterator.next() ;
                if (children_entry.getKey().equals("children")) {
                    decodeJson(JSONArray.fromObject(children_entry.getValue()), modulePowerBean) ;
                } else if (children_entry.getKey().equals("powers")) {
                    JSONArray powerJsonArr = JSONArray.fromObject(children_entry.getValue()) ;
                    for (int j = 0; j < powerJsonArr.size(); j++) {
                        @SuppressWarnings("unchecked")
                        Iterator<Map.Entry<String, Integer>> powerIterator = ((Map<String, Integer>) powerJsonArr.get(j)).entrySet().iterator() ;
                        while (powerIterator.hasNext()) {
                            Map.Entry<String, Integer> power_entry = (Map.Entry<String, Integer>) powerIterator.next() ;
                            if (!power_entry.getKey().equals("label")) {
                                powerList.add(new PowerBean(power_entry.getKey(), (power_entry.getValue()))) ;
                            }
                        }
                    }
                } else {
                    if (children_entry.getKey().toLowerCase().equals("id")) {
                        treeId = (String) children_entry.getValue() ;
                    }
                }
            }
            modulePowerBean.put(treeId, powerList) ;
        }
    }

}
