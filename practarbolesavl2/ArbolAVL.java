
public class ArbolAVL {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        //Arbol con rotación doble a la izquierda
        Arbol a1 = new Arbol();    
                
        a1.put(5);
        a1.put(3);
        a1.put(10);
        a1.put(7);
        a1.put(12);        
        a1.put(6);
        
        System.out.println("ARBOL 1: Recorrido en PreOrden tras una rotación doble a la izquierda al insertar el 6");
        a1.preOrden(a1.root);
        System.out.println();
        
        //Arbol con rotación doble a la derecha
        Arbol a2 = new Arbol();        
        a2.put(9);
        a2.put(5);
        a2.put(10);
        a2.put(1);
        a2.put(7);
        a2.put(6);
        
        System.out.println("ARBOL 2: Recorrido en PreOrden tras una rotación doble a la derecha al insertar el 6");
        a2.preOrden(a2.root);
        System.out.println();
        
        System.out.println("ARBOL 1: Recorrido en PreOrden tras eliminar el 6");
        a1.delete(6);
        a1.preOrden(a1.root);
        System.out.println();
        
        System.out.println("ARBOL 2: Recorrido en PreOrden tras eliminar el 6");
        a2.delete(6);
        a2.preOrden(a2.root);
        System.out.println();
        
        //Lo suyo es preguntar si el árbol no está vacío antes de borrar nada.
        if (!a1.isEmpty())
        {
            a1.delete(7);
        }
        if (!a1.isEmpty())
        {
            a1.delete(12);
        }
        if (!a1.isEmpty())
        {
            a1.delete(3);
        }
        if (!a1.isEmpty())
        {
            a1.delete(10);
        }
        if (!a1.isEmpty())
        {
            a1.delete(5);
        }
                  
        //Lo suyo es preguntar si el árbol no está vacío antes de imprimir nada
        if (!a1.isEmpty())
        {
            a1.preOrden(a1.root);
        }
        else
        {
            System.out.println("Árbol vacío");
        }
        
        System.out.println("ARBOL 2: Buscando el 5");
        if (a2.find(5, a2.root)!= null)
        {
            System.out.println("Valor encontrado!");
        }
        else
        {
            System.out.println("No se encuentra el valor");
        }
    }
    
}