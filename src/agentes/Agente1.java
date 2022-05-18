/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package agentes;

import agnteinitial.Contenedor;
import contenidoSerializado.Sensores;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author davpa
 */
public class Agente1 extends Agent{

    
    //necesita un comportamiento
    @Override
    protected void setup() {//metodo que se ejecuta siempre primero, todo lo que agamos fuera hay que meterlo a llamar aqui
        //configuracion inicial
        addBehaviour(new comportamiento());
    }
    //tenemos los comportamientos, que se pueden controlar del agente sea ciclico secuencial, etc
    //podemos crear una clase(interna subclase) solo de comportamientos

    @Override
    protected void takeDown() {//ultima accion antes de morir, este se ejecuta una vez llamado doDelete()
        Contenedor c = (Contenedor)getArguments()[0];//obtenemos el conocimiento
        int i = Integer.parseInt(getArguments()[1].toString());
        i++;
        c.crearHijos("AgenteHijo"+i, new Object[]{c,i});//habra de hacer un bucle para que el alias vaya cambiando,>>(ya esta hecho) con el i++ ya se hizo
        System.out.println("Muere"); //y asi cada que se muera el agente, se creara un hijo diferente
        System.out.println("essss");
    }
    
    
    
    class comportamiento extends Behaviour{//comportamiento controlado

        boolean terminado = false;
        @Override
        public void action() {
            //todo lo que necesite hacer el agente, red neuronal, AG, Bayes, if-else
            System.out.println(getName());
            //sensor de temperatura........
            //aqui algun codigo que cada hora pida el valor de la temperatura al
            //Mensajes.enviar(ACLMessage.INFORM, "BuscarDatos", "40", "COD0102", getAgent());//se envia el mensaje recibiendo solo 1 dato
            Mensajes.enviarS(ACLMessage.INFORM, "BuscarDatos", new Sensores("alto",40), "COD0102", getAgent());//se envio el mensaje serializado y recibiendo un objeto

            ACLMessage acl = blockingReceive();
            System.out.println(acl.getContent());
        }

        @Override
        public boolean done() {
            return terminado;//con true lo hace una sola vez
        }
        
    }
    
    
    
    
    
}
