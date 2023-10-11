/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

/**
 *
 * @author Belfer
 */
public class ZlyNumerDnia extends RuntimeException{
    ZlyNumerDnia(String s){
        super(s);
    }
    ZlyNumerDnia(Throwable t){
        super(t);
    }
    ZlyNumerDnia(String s, Throwable t){
        super(s, t);
    }
    ZlyNumerDnia(){};
}