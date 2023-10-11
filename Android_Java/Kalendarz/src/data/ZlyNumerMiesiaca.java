package data;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Belfer
 */
public class ZlyNumerMiesiaca extends RuntimeException{
    ZlyNumerMiesiaca(String s){
        super(s);
    }
    ZlyNumerMiesiaca(Throwable t){
        super(t);
    }
    ZlyNumerMiesiaca(String s, Throwable t){
        super(s, t);
    }
    ZlyNumerMiesiaca(){};
}
