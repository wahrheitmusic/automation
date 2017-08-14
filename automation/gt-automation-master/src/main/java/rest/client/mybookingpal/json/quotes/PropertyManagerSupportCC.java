package rest.client.mybookingpal.json.quotes;

public class PropertyManagerSupportCC
{
    private String supportMC;

    private String supportVISA;

    private String none;

    private String supportAE;

    private String supportJCB;

    private String supportDISCOVER;

    private String supportDINERSCLUB;

    public String getSupportMC ()
    {
        return supportMC;
    }

    public void setSupportMC (String supportMC)
    {
        this.supportMC = supportMC;
    }

    public String getSupportVISA ()
    {
        return supportVISA;
    }

    public void setSupportVISA (String supportVISA)
    {
        this.supportVISA = supportVISA;
    }

    public String getNone ()
    {
        return none;
    }

    public void setNone (String none)
    {
        this.none = none;
    }

    public String getSupportAE ()
    {
        return supportAE;
    }

    public void setSupportAE (String supportAE)
    {
        this.supportAE = supportAE;
    }

    public String getSupportJCB ()
    {
        return supportJCB;
    }

    public void setSupportJCB (String supportJCB)
    {
        this.supportJCB = supportJCB;
    }

    public String getSupportDISCOVER ()
    {
        return supportDISCOVER;
    }

    public void setSupportDISCOVER (String supportDISCOVER)
    {
        this.supportDISCOVER = supportDISCOVER;
    }

    public String getSupportDINERSCLUB ()
    {
        return supportDINERSCLUB;
    }

    public void setSupportDINERSCLUB (String supportDINERSCLUB)
    {
        this.supportDINERSCLUB = supportDINERSCLUB;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [supportMC = "+supportMC+", supportVISA = "+supportVISA+", none = "+none+", supportAE = "+supportAE+", supportJCB = "+supportJCB+", supportDISCOVER = "+supportDISCOVER+", supportDINERSCLUB = "+supportDINERSCLUB+"]";
    }
}