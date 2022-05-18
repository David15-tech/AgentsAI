/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package agentes;

import agnteinitial.Contenedor;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

/**
 *
 * @author davpa
 */
public class Agente3 extends Agent{

    
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
        c.crearHijos("AgenteHijo"+i, new Object[]{c,i});//habra de hacer un bucle para que el alias vaya cambiando, con el i++ ya se hizo
        System.out.println("Muere");
    }
    
    
    
    class comportamiento extends Behaviour{//comportamiento controlado

        boolean terminado = false;
        @Override
        public void action() {
            //todo lo que necesite hacer el agente, red neuronal, AG, Bayes, if-else
            System.out.println("=============NOMBRE DEL AGENTRE 3 el GetName========");
            System.out.println(getName());
            //terminado = true;//dependiendo lo que se necesite....si no ejecuta esta linea siempre se ejucutara de manera infinita
            //doDelete();//matar al agente, liberas memoria, lo quitas de contenedor ya no se usa(comentar el teminado, pa ejecutar)
            
            //sensor de Humedad........
            //altas o bajas
            //Mensajes.enviar(ACLMessage.INFORM, "BuscarDatos", "alta", "COD0302", getAgent());//se envio el mensaje
            //ACLMessage acl = blockingReceive();
            //System.out.println(acl.getContent());
        }

        @Override
        public boolean done() {
            return terminado;//con true lo hace una sola vez
        }
        
    }
    
    
    
    
    
}
