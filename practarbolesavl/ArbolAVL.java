/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 * http://163.10.22.82/OAS/AVL_Rotaciones/rotacin_simple_derecha.html
 */
package practarbolesavl;

import javax.swing.JOptionPane;

/**
 *
 * @author Andres  Arenas
 */
public class ArbolAVL {
    private NodoArbolAVL raiz;
    public ArbolAVL(){
        raiz=null;
    }
    //Metodo para buscar un Nodo
    public NodoArbolAVL buscar(int d, NodoArbolAVL r){
        if(raiz==null){
            return null;
        }else if(r.dato==d){
            return r;
        }else if(r.dato<d){
            return buscar(d,r.hijoDerecho);
        }else{
            return buscar(d,r.hijoIzquierda);
        }
    }
    //Obtener el Factor de Equilibrio
    public int obtenerFE(NodoArbolAVL x){
        if(x==null){
            return -1;
        }else{
            return x.fe;
        }
    }
    //Rotacion Simple Derecha
    public NodoArbolAVL rotacionDerecha(NodoArbolAVL c){
        NodoArbolAVL auxiliar = c.hijoIzquierda;
        c.hijoIzquierda = auxiliar.hijoDerecho;
        auxiliar.hijoDerecho=c;
        c.fe=Math.max(obtenerFE(c.hijoIzquierda),obtenerFE(c.hijoDerecho))+1;
        auxiliar.fe = Math.max(obtenerFE(auxiliar.hijoIzquierda), obtenerFE(auxiliar.hijoDerecho))+1;
        return auxiliar;
    }
    //Rotacion  Simple Izquierda
    public NodoArbolAVL rotacionIzquierda(NodoArbolAVL c){
        NodoArbolAVL auxiliar = c.hijoDerecho;
        c.hijoIzquierda = auxiliar.hijoIzquierda;
        auxiliar.hijoIzquierda=c;
        c.fe=Math.max(obtenerFE(c.hijoIzquierda),obtenerFE(c.hijoDerecho))+1;
        auxiliar.fe = Math.max(obtenerFE(auxiliar.hijoIzquierda), obtenerFE(auxiliar.hijoDerecho))+1;
        return auxiliar;
    }
    //Rotacion Doble a la Izquierda
    public NodoArbolAVL rotacionDobleIzquierda(NodoArbolAVL c){
        NodoArbolAVL temporal;
        c.hijoDerecho=rotacionDerecha(c.hijoDerecho);
        temporal = rotacionIzquierda(c);
        return temporal;
    }
    //Rotacion Doble a la Derecha
    public NodoArbolAVL rotacionDobleDerecha(NodoArbolAVL c){
        NodoArbolAVL temporal;
        c.hijoDerecho=rotacionIzquierda(c.hijoDerecho);
        temporal=rotacionDerecha(c);
        return temporal;
    }
    //insertar avl
public NodoArbolAVL insertarAVL(NodoArbolAVL nuevo, NodoArbolAVL subAr){
    NodoArbolAVL nuevoPadre=subAr;
    if(nuevo.dato<subAr.dato){
        if(subAr.hijoIzquierda==null){
            subAr.hijoIzquierda=nuevo;
        }else{
            subAr.hijoIzquierda=insertarAVL(nuevo, subAr.hijoIzquierda);
            if((obtenerFE(subAr.hijoIzquierda)-obtenerFE(subAr.hijoDerecho)==2)){
                if(nuevo.dato<subAr.hijoIzquierda.dato){
                    nuevoPadre=rotacionIzquierda(subAr);
                }else{
                    nuevoPadre=rotacionDobleIzquierda(subAr);
                }
            }
        }
    }else if(nuevo.dato>subAr.dato){
        if(subAr.hijoDerecho==null){
            subAr.hijoDerecho=nuevo;
        }else{
            subAr.hijoDerecho=insertarAVL(nuevo, subAr.hijoDerecho);
            if((obtenerFE(subAr.hijoDerecho)-obtenerFE(subAr.hijoIzquierda)==2)){
                if(nuevo.dato>subAr.hijoDerecho.dato){
                    nuevoPadre=rotacionDerecha(subAr);
                }else{
                    nuevoPadre=rotacionDobleDerecha(subAr);
                }
            }
        }
    }else{
        JOptionPane.showMessageDialog(null, "Nodo duplicado");
    }

  //actualizar altura
    if((subAr.hijoIzquierda==null)&&(subAr.hijoDerecho!=null)){
        subAr.fe=subAr.hijoDerecho.fe+1;
    }else if((subAr.hijoDerecho==null)&&(subAr.hijoIzquierda!=null)){
        subAr.fe=subAr.hijoIzquierda.fe+1;
    }else{
        subAr.fe=Math.max(obtenerFE(subAr.hijoIzquierda), obtenerFE(subAr.hijoDerecho))+1;
    }
    return nuevoPadre;
}


//insertar normal
public void insertar(int d){
   NodoArbolAVL nuevo= new NodoArbolAVL(d);
   if(raiz==null){
       raiz=nuevo;
   }else{
       raiz=insertarAVL(nuevo, raiz);
   }
}

//recorridos
    //recorrer in orden
    public void inOrden(NodoArbolAVL r){
        if(r!=null){
            inOrden(r.hijoIzquierda);
            System.out.println(r.dato);
            inOrden(r.hijoDerecho);
        }
    }
    //recorrer en preorden
    public void preorden(NodoArbolAVL r){
        if(r!=null){
            System.out.println(r.dato);
            preorden(r.hijoIzquierda);
            preorden(r.hijoDerecho);
        }
    }
    //recorrer postorden
    public void postOrden(NodoArbolAVL r){
         if(r!=null){
            postOrden(r.hijoIzquierda);
            postOrden(r.hijoDerecho);
             System.out.println(r.dato);
        }
    }

   public boolean estaVacio(){
        return raiz==null;
    }
}
