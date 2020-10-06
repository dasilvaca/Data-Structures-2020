package structs;

public class DynamicArray {
    Object[] array;
    private int size, capacity;

    DynamicArray(){
        size = 0;
        capacity = 1;
    }
    
    <T>DynamicArray(T[] arreglo){
        array = arreglo;
        size = 0;
        capacity = 1;
    }

    public <T> void append(T data){
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

}