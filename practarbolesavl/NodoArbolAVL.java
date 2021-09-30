/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practarbolesavl;

/**
 *
 * @author user
 */
public class NodoArbolAVL {
    int dato, fe;
    NodoArbolAVL hijoIzquierda, hijoDerecho;
    public NodoArbolAVL(int d){
        this.dato=d;
        this.fe=0;
        this.hijoIzquierda=null;
        this.hijoDerecho=null;
    }
}
