package rest.client.mybookingpal.json.getinfo;

public class GetInfoResponse
{
    private String error;

    private String error_message;

    private Data data;

    public String getError ()
    {
        return error;
    }

    public void setError (String error)
    {
        this.error = error;
    }

    public String getError_message ()
    {
        return error_message;
    }

    public void setError_message (String error_message)
    {
        this.error_message = error_message;
    }

    public Data getData ()
    {
        return data;
    }

    public void setData (Data data)
    {
        this.data = data;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [error = "+error+", error_message = "+error_message+", data = "+data+"]";
    }
}