package structs;

public class DynamicArray {
    Object[] array;
    public int size, capacity;

    public DynamicArray(){ 
        size = 0;
        capacity = 1;
        array= new Object[4];
    }
    
     DynamicArray(Object[] arreglo){
        array = arreglo;
        size =arreglo.length -1;
        capacity = arreglo.length;
    }

    public void append(Object data){
        if (size + 1 == capacity){
            Object temparray[] = new Object[2*capacity];
            for(int i =0; i < capacity; i ++){
                temparray[i] = array[i];
            }
            array = temparray;
            array[size] = data;
            capacity *=2;
            size ++;
        }
        else{
            array[size] = data;
            size ++;
        }
    }

    public Object get(int index){
        return (Object)array[index];
    }
       

}