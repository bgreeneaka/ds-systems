/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package session.logging;

import javax.ejb.Local;

/**
 *
 * @author chromodynamics
 */
@Local
public interface MessagingBeanLocal {

    void sendMessage(Object msg);
    
}
