package com.diogo.mobile.app.model;

import java.math.BigInteger;
import java.sql.Date;

/**
 * Created on 08/10/2015 16:24.
 *
 * @author Diogo Oliveira.
 */
public class Device
{
    public static final String ID = "id";
    public static final String REGISTRATION_ID = "registationId";
    public static final String REGISTRATION_DATE = "registationDate";

    private int id;
    private String registrationId;
    private BigInteger registrationDate = BigInteger.ZERO;

    public Device(String registrationId)
    {
        this.registrationId = registrationId;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getRegistrationId()
    {
        return registrationId;
    }

    public void setRegistrationId(String registrationId)
    {
        this.registrationId = registrationId;
    }

    public BigInteger getRegistrationDate()
    {
        return registrationDate;
    }

    public void setRegistrationDate(BigInteger registrationDate)
    {
        this.registrationDate = registrationDate;
    }
}
