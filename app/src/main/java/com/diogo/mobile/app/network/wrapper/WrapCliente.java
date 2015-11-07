package com.diogo.mobile.app.network.wrapper;

import com.diogo.mobile.app.model.Cliente;
import com.diogo.mobile.app.network.WrapObjectToNetwork;

/**
 * Created on 13/10/2015 10:57.
 *
 * @author Diogo Oliveira.
 */
public class WrapCliente extends WrapObjectToNetwork
{
    public WrapCliente(int method, Cliente cliente)
    {
        super(method, cliente);
    }
}
