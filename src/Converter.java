import java.util.TreeMap;

public class Converter {
    TreeMap<Character,Integer> romanKeyMap = new TreeMap<>();
    TreeMap<Integer,String> arabianKeyMap = new TreeMap<>();
    public Converter(){
        romanKeyMap.put('I',1);
        romanKeyMap.put('V',5);
        romanKeyMap.put('X',10);

        arabianKeyMap.put(10,"X");
        arabianKeyMap.put(9,"IX");
        arabianKeyMap.put(5,"V");
        arabianKeyMap.put(4,"IV");
        arabianKeyMap.put(1,"I");
    }
    public boolean isRoman(String number){
        return romanKeyMap.containsKey(number.charAt(0));
    }
    public String intToRoman(int number){
        String roman ="";
        int arabianKey;
        do{
            arabianKey = arabianKeyMap.floorKey(number);
            roman += arabianKeyMap.get(arabianKey);
            number -= arabianKey;
        }while ((number !=0));
        return roman;
    }
    public int romanToInt(String s){
        int end =s.length()-1;
        char[] arr = s.toCharArray();
        int arabian;
        int result = romanKeyMap.get(arr[end]);
        for(int i =end -1; i>=0; i--){
            arabian = romanKeyMap.get(arr[i]);
            if(arabian<romanKeyMap.get(arr[i+1])){
                result= arabian;
            }else{
                result+=arabian;
            }
        }
        return result;
    }
}
