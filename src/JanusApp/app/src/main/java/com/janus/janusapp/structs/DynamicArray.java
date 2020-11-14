package com.janus.janusapp.structs;

import com.janus.janusapp.classes.*;
public class DynamicArray<T> {
    public Object[] array;
    public int size, capacity;

    public DynamicArray(){ 
        size = 0;
        capacity = 1;
        array=new Object[1];
    }
    
     DynamicArray(T[] arreglo){
        array = arreglo;
        size =arreglo.length -1;
        capacity = arreglo.length;
    }

    public void append(T data){
        if (size + 1 == capacity){
            Object temparray[] = new Object[2*capacity];
            
            for(int i =0; i < size; i ++){
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
    public void insert(int index,T data ){
        if (size + 1 == capacity){
            Object temparray[] = new Object[2*capacity];
            int contador=0;
            while(contador<index){
                temparray[contador]=array[contador];
                contador+=1;
            } 
            temparray[contador]=data;
            contador+=1;
            
                
                
            while(contador<size+1){
                temparray[contador]=array[contador-1];
                contador+=1;
                
            }
            array = temparray;
            
            capacity *=2;
            size ++;
        }else{
            for(int i=size;i>index;i--){
                array[i]=array[i-1];
            }
            size++;
            array[index]=data;
        }
    }

    public T get(int index){
        @SuppressWarnings("unchecked")
        T data = (T)array[index];
        return data;
    }
    @SuppressWarnings("unchecked")
    public Project orderedAddProject(Project newProject){
        int inicio=0;
        int fin=size-1;
        int medio;
        Project proyecto;
        while(inicio<=fin){
            medio=(inicio+fin)/2;
            proyecto= (Project)array[medio];
            if(proyecto.name.equals(newProject.name)){
                System.out.println("El proyecto ya existe");
                return null;
            }else if(proyecto.name.compareTo(newProject.name)>0){
                fin=medio-1;
            }else{
                inicio=medio+1;
            }
        }
        
        this.insert(inicio, (T) newProject);
        return null;
    }
    @SuppressWarnings("unchecked")
    public User orderedAddUser(User newUser){
        int inicio=0;
        int fin=size-1;
        int medio;
        User usuario;
        while(inicio<=fin){
            medio=(inicio+fin)/2;
            usuario= (User)array[medio];
            if(usuario.username.equals(newUser.username)){
                System.out.println("El usuario ya existe");
                return null;
            }else if(usuario.username.compareTo(newUser.username)>0){
                fin=medio-1;
            }else{
                inicio=medio+1;
            }
        }
        this.insert(inicio, (T)newUser);
        return null;
    }

       
    public User getUser(String userName,String userPassword){
        int inicio=0;
        int fin=size-1;
        int medio;
        User usuario;
        while(inicio<=fin){
            medio=(inicio+fin)/2;
            usuario=(User)array[medio];
            if(usuario.username.equals(userName) && usuario.password.equals(userPassword)){
                return usuario;
            }else if(usuario.username.compareTo(userName)<0){
                inicio=medio+1;
            }else {
                fin=medio-1;
            }
        }
        return null;
    }
    public Project getProject(String projectname){
        int inicio=0;
        int fin=size-1;
        int medio;
        Project proyecto;
        while(inicio<=fin){
            medio=(inicio+fin)/2;
            proyecto=(Project)array[medio];
            if(proyecto.name.equals(projectname)){
                return proyecto;
            }else if(proyecto.name.compareTo(projectname)<0){
                inicio=medio+1;
            }else {
                fin=medio-1;
            }
        }
        System.out.println("The project does not exist");
        return null;
    }
    
    @Override
    public String toString(){
        String arreglo="";
        for(int i=0;i<size;i++){
            arreglo+=array[i].toString()+" ";
        }
        return arreglo;
    }

}