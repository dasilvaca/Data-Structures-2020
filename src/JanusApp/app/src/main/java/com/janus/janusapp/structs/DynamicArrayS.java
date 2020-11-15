package com.janus.janusapp.structs;

import com.janus.janusapp.classes.Project;
import com.janus.janusapp.classes.User;

public class DynamicArrayS {
    public String[] array=new String[1];
    public int size, capacity;

    public DynamicArrayS() {
        size = 0;
        capacity = 1;
        array = new String[1];
    }

    public DynamicArrayS(String[] arreglo) {
        array = arreglo;
        size = arreglo.length - 1;
        capacity = arreglo.length;
    }

    public void append(String data) {
        if (size + 1 == capacity) {
            String temparray[] = new String[2 * capacity];

            for (int i = 0; i < size; i++) {
                temparray[i] = array[i];
            }
            array = temparray;
            array[size] = data;
            capacity *= 2;
            size++;
        } else {
            array[size] = data;
            size++;
        }
    }

    public void insert(int index, String data) {
        if (size + 1 == capacity) {
            String temparray[] = new String[2 * capacity];
            int contador = 0;
            while (contador < index) {
                temparray[contador] = array[contador];
                contador += 1;
            }
            temparray[contador] = data;
            contador += 1;


            while (contador < size + 1) {
                temparray[contador] = array[contador - 1];
                contador += 1;

            }
            array = temparray;

            capacity *= 2;
            size++;
        } else {
            for (int i = size; i > index; i--) {
                array[i] = array[i - 1];
            }
            size++;
            array[index] = data;
        }
    }

    public String get(int index) {
        @SuppressWarnings("unchecked")
        String data = array[index];
        return data;
    }

    @SuppressWarnings("unchecked")


    @Override
    public String toString() {
        String arreglo = "";
        for (int i = 0; i < size; i++) {
            arreglo += array[i].toString() + " ";
        }
        return arreglo;
    }
}
