package rest.client.mybookingpal.json.getinfo;

public class Data
{
    private String ID;

    public String getID ()
    {
        return ID;
    }

    public void setID (String ID)
    {
        this.ID = ID;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [ID = "+ID+"]";
    }
}