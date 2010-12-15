package tc.misc.dp;

import java.util.HashMap;
import java.util.Map;

public class MoneyExchange {
  Map<String, Double> cache = new HashMap<String, Double>();
  private String[] rates;

  public double bestRate(
      String[] rates, String type1, String type2) {
    this.rates = rates; 
    return solve(type1, type2);
  }

  private double solve(String type1, String type2) {
    if (type1.equals(type2))
      return 1;
    
    if (cache.containsKey(type1 + " " + type2));
      
    
    double max = -1;
    for (String rate : rates) {
      String[] components = rate.split(" +");
      if (components[0].equals(type1)) {
        double num1 = Double.valueOf(components[1]);
        double num2 = Double.valueOf(components[3]);
        double _max = num2 / num1 * solve(components[2], type2);
        if ( _max > max)
          max = _max;
      }
    }
    
    cache.put(type1 + " " + type2, max);
    return max;
  }
  
  public static void main(String[] _) {
    System.out.println(
        new MoneyExchange().bestRate(
            new String[]{"EUR 8 USD 10", "USD 10 EUR 7"},
            "EUR", "USD"));
    
    System.out.println(
        new MoneyExchange().bestRate(
            new String[]{"EUR 0008 USD 0010"},
            "USD", "EUR"));
    
    System.out.println(
        new MoneyExchange().bestRate(
            new String[]{"EUR 8 USD 10","EUR 1 GOODBUY 2","GOODBUY 1 USD 2"},
            "EUR", "USD"));
    
    System.out.println(
        new MoneyExchange().bestRate(
            new String[]{"A 1 B 2","A 1 C 3","B 2 D 3","C 9 D 10","D 7 E 2"},
            "A", "E"));
    
    System.out.println(
        new MoneyExchange().bestRate(
            new String[]{},
            "TYPE1", "TYPE2"));
    
    System.out.println(
        new MoneyExchange().bestRate(
            new String[]{"A 7 A 5","A 1 B 1","B 2 A 2"},
            "A", "A"));

    System.out.println(
        new MoneyExchange().bestRate(
            new String[]{
                "UIDY 1 WKRR 10", "WKRR 1 SEIM 10", "SEIM 1 SQCP 10", 
                "SQCP 1 BFWZ 10", "BFWZ 1 BPNH 10", "BPNH 1 SSGR 10", 
                "SSGR 1 IDOE 10", "IDOE 1 OSEK 10", "OSEK 1 EXBY 10", 
                "EXBY 1 CMKW 10", "CMKW 1 CMNG 10", "CMNG 1 UJFV 10", 
                "UJFV 1 BUET 10", "BUET 1 MKMW 10", "MKMW 1 QCCE 10", 
                "QCCE 1 WHOA 10", "WHOA 1 ERAI 10", "ERAI 1 FKFI 10", 
                "FKFI 1 ZSQT 10", "ZSQT 1 EVQH 10", "EVQH 1 RWCG 10", 
                "RWCG 1 IOCY 10", "IOCY 1 SFEP 10", "SFEP 1 OSPS 10", 
                "OSPS 1 MQDR 10", "MQDR 1 CKBB 10", "CKBB 1 ERWK 10", 
                "ERWK 1 OORG 10", "OORG 1 KVMT 10", "KVMT 1 LQRE 10",
                "LQRE 1 XWTL 10", "XWTL 1 QIEE 10", "QIEE 1 AJYF 10", 
                "AJYF 1 TZIX 10", "TZIX 1 TFIH 10", "TFIH 1 VBPG 10", 
                "VBPG 1 XDBI 10", "XDBI 1 UUMR 10", "UUMR 1 SFDL 10",
                "SFDL 1 QJPS 10", "QJPS 1 SNXN 10", "SNXN 1 PGKI 10", 
                "PGKI 1 NURI 10", "NURI 1 YJOV 10", "YJOV 1 MRFG 10", 
                "MRFG 1 MUAE 10", "MUAE 1 BDPR 10", "BDPR 1 OHMG 10",
                "OHMG 1 UJVL 10", "UJVL 1 RFVE 10"
            }, "UIDY", "RFVE"));
    
    System.out.println(
        new MoneyExchange().bestRate(
            new String[]{
                "VVUI 10 CUGN 1", "CUGN 10 LNVG 1", "LNVG 10 NQLV 1", 
                "NQLV 10 CRGK 1", "CRGK 10 VXCG 1", "VXCG 10 ZYPK 1", 
                "ZYPK 10 IIAF 1", "IIAF 10 FVQH 1", "FVQH 10 RWUC 1", 
                "RWUC 10 KSKZ 1", "KSKZ 10 IWUM 1", "IWUM 10 NDZK 1", 
                "NDZK 10 CDQD 1", "CDQD 10 BHNL 1", "BHNL 10 RQSW 1", 
                "RQSW 10 LIFC 1", "LIFC 10 HCHT 1", "HCHT 10 URSE 1", 
                "URSE 10 NPQC 1", "NPQC 10 SPMU 1", "SPMU 10 UFXX 1", 
                "UFXX 10 OMIG 1", "OMIG 10 EBEP 1", "EBEP 10 LKUS 1", 
                "LKUS 10 MDLG 1", "MDLG 10 UEMK 1", "UEMK 10 VCMP 1", 
                "VCMP 10 UBJQ 1", "UBJQ 10 IIOW 1", "IIOW 10 WWEB 1", 
                "WWEB 10 ZJSN 1", "ZJSN 10 VOFJ 1", "VOFJ 10 RTPM 1", 
                "RTPM 10 ZDWU 1", "ZDWU 10 HKJD 1", "HKJD 10 LUUV 1", 
                "LUUV 10 EIUC 1", "EIUC 10 GYFI 1", "GYFI 10 JYVG 1", 
                "JYVG 10 OCPG 1", "OCPG 10 VGUU 1", "VGUU 10 JSQR 1", 
                "JSQR 10 CZUQ 1", "CZUQ 10 VOLB 1", "VOLB 10 WFED 1", 
                "WFED 10 GJLR 1", "GJLR 10 JIYY 1", "JIYY 10 KNGG 1", 
                "KNGG 10 UACD 1", "UACD 10 STWW 1"},
                "VVUI", "STWW"));
  }
}
