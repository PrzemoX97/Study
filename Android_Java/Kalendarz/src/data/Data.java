/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;
/**
 *
 * @author Student
 */

class miesiac{
    private int liczbaDni;
    private String nazwa,rzymskie;
    public miesiac (int liczbaDni, String nazwa, String rzymskie){
        this.liczbaDni=liczbaDni;
        this.nazwa=nazwa;
        this.rzymskie=rzymskie;
    }
    public int getLiczbaDni() {
        return liczbaDni;
    }
    public String getNazwa() {
        return nazwa;
    }
    public String getRzymskie(){
        return rzymskie;
    }
}
class miesiace{
   miesiac miesiac[]={
       new miesiac(31,"Styczen","I"),
       new miesiac(28,"Luty","II"),
       new miesiac(31,"Marzec","III"),
       new miesiac(30,"Kwiecien","IV"),
       new miesiac(31,"Maj","V"),
       new miesiac(30,"Czerwiec","VI"),
       new miesiac(31,"Lipiec","VII"),
       new miesiac(31,"Sierpien","VIII"),
       new miesiac(30,"Wrzesien","IX"),
       new miesiac(31,"Pazdziernik","X"),
       new miesiac(30,"Listopad","XI"),
       new miesiac(31,"Grudzien","XII"),
   };
   public int getIlośćDni(int n) throws ZlyNumerMiesiaca{
       try{
           return miesiac[n-1].getLiczbaDni();
       }catch(ArrayIndexOutOfBoundsException e){
           throw new ZlyNumerMiesiaca("Błędny miesiąc.");     
       }
   }
   public String getNazweMiesiaca(int n){
       return miesiac[n-1].getNazwa();
   }
   public String getRzymskie(int n){
       return miesiac[n-1].getRzymskie();
   }
}
public class Data extends miesiace {
    private int dzien,miesiac,rok;
    public static final int FORMAT_KROTKI = 991;
    public static final int FORMAT_DLUGI = 992;
    public static final int FORMAT_SREDNI = 993;
    static final String tydzien[] = {"Niedziela","Poniedzialek","Wtorek","Sroda","Czwartek","Piatek","Sobota"};
    static final Data POCZATEK_TYGODNIA = new Data(9,4,2018);
    
    /**
     * @param args the command line arguments
     */
    public Data(int dzien, int miesiac, int rok) throws ZlyNumerDnia {
        this.miesiac = miesiac;
        if(sprawdzDzien(dzien, miesiac)){
            this.dzien = dzien;
        }else{
            throw new ZlyNumerDnia("Zły dzień");
        }
        this.rok = rok;
    }
    private boolean isPrzestepny(int rok)
    {
        return (this.rok % 4 == 0 && this.rok % 100 != 0 || this.rok % 400 == 0);
    } 
    public int getDzien(){
        return this.dzien;
    }
    public int getMiesiac(){
        return this.miesiac;
    }
    public String getNazweMiesiaca(){
        return getNazweMiesiaca(this.miesiac);
    }
    public String getRzymskie(){
        return getRzymskie(this.miesiac);
    }
    public int getRok(){
        return this.rok;
    }
    public int compateTo(Data data){
        
        return 0;
    }
    public String getNazweDnia(){
        //Innaczej nie potrafie
        int year = this.rok;
        int day = (((year - 1) * 365) + ((year - 1) / 4) - ((year - 1) / 100) + ((year) / 400) + 1) % 7;
        return tydzien[day];
    }
    public void tydzienPlus(){
        if(isPrzestepny(this.rok) && this.miesiac ==2){
            if(this.dzien + 7 < getIlośćDni(miesiac)+1){
                this.dzien +=7;
            }else{
               this.dzien = this.dzien+7-getIlośćDni(miesiac);
               if(this.miesiac < 12){
                  this.miesiac = this.miesiac+1;
               }else{
                  this.rok+=1;
                  this.miesiac = 1;
               }
            } 
        }else{
            if(this.dzien + 7 < getIlośćDni(miesiac)){
                this.dzien +=7;
            }else{
               this.dzien = this.dzien+7-getIlośćDni(miesiac);
               if(this.miesiac < 12){
                  this.miesiac = this.miesiac+1;
               }else{
                  this.rok+=1;
                  this.miesiac = 1;
               }
            }
        }    
    }
    public void tydzienMinus(){
        if(isPrzestepny(this.rok) && this.miesiac ==2){
            if(this.dzien - 7 > 1){
                    this.dzien -=7;
            }else{
               this.dzien = this.dzien-7+getIlośćDni(miesiac);
                if(this.miesiac > 1){
                   this.miesiac = this.miesiac-1;
                }else{
                   this.rok-=1;
                   this.miesiac = 12;
                }
            }
        }else{
            if(this.dzien - 7 > 1){
                    this.dzien -=7;
            }else{
               this.dzien = this.dzien-7+getIlośćDni(miesiac);
                if(this.miesiac > 1){
                   this.miesiac = this.miesiac-1;
                }else{
                   this.rok-=1;
                   this.miesiac = 12;
                }
            }
        }
        
    }
    public String toString(int typ){
        if(typ == FORMAT_KROTKI){
            return dzien+"/"+miesiac+"/"+rok;
        }
        else if(typ == FORMAT_DLUGI){    
            return this.getNazweDnia()+","+dzien+" "+this.getNazweMiesiaca()+" "+rok;
        }
        else if(typ == FORMAT_SREDNI){
            return dzien+" "+this.getRzymskie()+" "+rok;
        }
        else{
            return "Nieznany typ";
        }
    }  
    public boolean sprawdzDzien(int dzien, int miesiac){
        if(dzien < getIlośćDni(miesiac)+1 && dzien > 0){
            return true;
        }else{
            return false;
        }
    }
    public static void main(String[] args) {
        // TODO code application logic here
        //int MarginLeft = 10;
        //int MarginTop = 10;
        /*
        NewJFrame frame;
        frame = new NewJFrame();
        frame.setVisible(true);
        
        frame.setBounds(100, 100, 480, 150);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        frame.setResizable(false);
        JTextField textField_1 = new JTextField();
        textField_1.setBounds(0+MarginLeft, 30, 86, 20);
        JTextField textField_2 = new JTextField();
        textField_2.setBounds(128+MarginLeft, 30, 86, 20);
        JTextField textField_3 = new JTextField();
        textField_3.setBounds(256+MarginLeft, 30, 86, 20);
        JTextField textField_4 = new JTextField();
        textField_4.setBounds(120, 90, 120, 20);
        JLabel label1 = new JLabel("Rok");
        label1.setBounds(30+MarginLeft, 0+MarginTop, 46, 14);
        JLabel label2 = new JLabel("Miesiac");
        label2.setBounds(150+MarginLeft, 0+MarginTop, 46, 14);
        JLabel label3 = new JLabel("Dzien");
        label3.setBounds(285+MarginLeft, 0+MarginTop, 46, 14);
        frame.getContentPane().add(label1);
        JButton btn1 = new JButton("Ustaw");
        btn1.setBounds(380, 28, 89, 23);
        JButton btn2 = new JButton("Cofnij tydzien");
        btn2.setBounds(50, 60, 110, 23);
        JButton btn3 = new JButton("Dodaj tydzien");
        btn3.setBounds(180, 60, 110, 23);
        frame.getContentPane().add(textField_1);
        frame.getContentPane().add(textField_2);
        frame.getContentPane().add(textField_3);
        frame.getContentPane().add(textField_4);
        frame.getContentPane().add(label1);
        frame.getContentPane().add(label2);
        frame.getContentPane().add(label3);
        frame.getContentPane().add(btn1);
        frame.getContentPane().add(btn2);
        frame.getContentPane().add(btn3);
        frame.setVisible(true);
        */
        Data d = new Data(31,12,1997);
        System.out.print(d.toString(FORMAT_KROTKI)+"\n"+d.toString(FORMAT_SREDNI)+"\n"+d.toString(FORMAT_DLUGI));
    }
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        // TODO add your handling code here:
    } 
}
