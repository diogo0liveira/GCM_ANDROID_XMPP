package com.diogo.mobile.app.network;

/**
 * Created on 08/10/2015 16:23.
 *
 * @author Diogo Oliveira.
 */
public abstract class WrapObjectToNetwork
{
    private Object object;
    private int method;

    public WrapObjectToNetwork(int method, Object object)
    {
        this.object = object;
        this.method = method;
    }

    public Object getObject()
    {
        return object;
    }

    public void setObject(Object object)
    {
        this.object = object;
    }

    public int getMethod()
    {
        return method;
    }

    public void setMethod(int method)
    {
        this.method = method;
    }
}
