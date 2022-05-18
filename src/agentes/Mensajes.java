/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package agentes;

import jade.core.AID;
import jade.core.Agent;
import jade.domain.FIPANames;
import jade.lang.acl.ACLMessage;
import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author davpa
 */
public class Mensajes {
    public static void enviar(int tipo, String receptor, String contenidoMSJ, String codigoConversacion, Agent emisor){//metodo pa enviar mensaje--- puede ser de ida y vuelta infor o request
        ACLMessage acl = new ACLMessage(tipo); //infor o request
        
        
        AID aid = new AID();
        aid.setLocalName(receptor);//nombre del receptor
        
        //seteamos toda la info del mensaje
        acl.addReceiver(aid); //se agreaga el nombre del que recibe
        acl.setSender(emisor.getAID()); //nombre del otro agente, el que envia
        acl.setLanguage(FIPANames.ContentLanguage.FIPA_SL);//cualquier lenguaje, se puede inventar o usar el mismo del FIPA "FIPANames.ContentLanguage.FIPA_SL"
        acl.setContent(contenidoMSJ); //contenido del mensaje
        acl.setConversationId(codigoConversacion);//codigo con el que se comunicaran, se usa cuando tenemos multiples agentes, osea de identifica de que enlace se trata
        
        emisor.send(acl);
        
    }
    
    
    public static void enviarS(int tipo, String receptor, Serializable object, String codigoConversacion, Agent emisor) {//metodo pa enviar mensaje serializado--- puede ser de ida y vuelta infor o request
        
        
        ACLMessage acl = new ACLMessage(tipo); //infor o request
        
        
        AID aid = new AID();
        aid.setLocalName(receptor);//nombre del receptor
        
        //seteamos toda la info del mensaje
        acl.addReceiver(aid); //se agreaga el nombre del que recibe
        acl.setSender(emisor.getAID()); //nombre del otro agente, el que envia
        acl.setLanguage(FIPANames.ContentLanguage.FIPA_SL);//cualquier lenguaje, se puede inventar o usar el mismo del FIPA "FIPANames.ContentLanguage.FIPA_SL"
        try {
            //acl.setContent(contenidoMSJ); //contenido del mensaje
            acl.setContentObject(object);
        } catch (IOException ex) {
            Logger.getLogger(Mensajes.class.getName()).log(Level.SEVERE, null, ex);
        }
        acl.setConversationId(codigoConversacion);//codigo con el que se comunicaran, se usa cuando tenemos multiples agentes, osea de identifica de que enlace se trata
        
        emisor.send(acl);
        
    }
}
