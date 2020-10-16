package structs;

public class DynamicArray<T> {
    Object[] array;
    public int size, capacity;

    public DynamicArray(){ 
        size = 0;
        capacity = 1;
        array=new Object[4];
    }
    
     DynamicArray(T[] arreglo){
        array = arreglo;
        size =arreglo.length -1;
        capacity = arreglo.length;
    }

    public void append(T data){
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

    public T get(int index){
        @SuppressWarnings("unchecked")
        T data = (T)array[index];
        return data;
    }
       

}