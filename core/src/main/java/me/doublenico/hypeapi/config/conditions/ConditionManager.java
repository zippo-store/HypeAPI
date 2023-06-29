package me.doublenico.hypeapi.config.conditions;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class ConditionManager {

    private static List<Condition> conditions = new ArrayList<>();

    public ConditionManager(List<Condition> conditionList){
        conditions = conditionList;
    }

    public ConditionManager(Condition condition){
        conditions.add(condition);
    }

    public static List<Condition> getConditions(){
        return conditions;
    }

    public static void addCondition(Condition condition){
        conditions.add(condition);
    }

    public static void removeCondition(Condition condition){
        conditions.remove(condition);
    }

    public static void removeCondition(int index){
        conditions.remove(index);
    }

    public static void clearConditions(){
        conditions.clear();
    }

    public static boolean hasCondition(Condition condition){
        return conditions.contains(condition);
    }

    public static boolean hasCondition(int index){
        return conditions.size() > index;
    }

    public static Condition getCondition(int index){
        return conditions.get(index);
    }

    public static boolean isValid(String condition){
        for(Condition c : conditions){
            if(c.getName().equalsIgnoreCase(condition)){
                return true;
            }
        }
        return false;
    }

    public static Condition getCondition(String condition){
        for(Condition c : conditions){
            if(c.getName().equalsIgnoreCase(condition)){
                return c;
            }
        }
        return null;
    }

    public boolean check(Condition condition, Player player, String args){

        return condition.checkCondition(player, args);
    }


}
