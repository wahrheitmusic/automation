package rest.client.mybookingpal.json.search;

public class Quote {
    private String depart;

    private String deposit;

    private String guests;

    private String CheckInDayRequired;

    private String rack;

    private String bathroom;

    private String currency;

    private String taxrate;

    private String locationid;

    private String productid;

    private String quote;

    private String quantity;

    private String longitude;

    private String productname;

    private String xsl;

    private String exactmatch;

    private String alert;

    private String managerName;

    private String sto;

    private String cost;

    private String minstay;

    private String bedroom;

    private String arrive;

    private String checkin;

    private String price;

    private Quotedetail[] quotedetail;

    private String address;

    private String pictureLocation;

    private String[] attributes;

    private String latitude;

    private String bar;

    private String inquiryOnly;

    private String checkout;

    public String getDepart() {
        return depart;
    }

    public void setDepart(String depart) {
        this.depart = depart;
    }

    public String getDeposit() {
        return deposit;
    }

    public void setDeposit(String deposit) {
        this.deposit = deposit;
    }

    public String getGuests() {
        return guests;
    }

    public void setGuests(String guests) {
        this.guests = guests;
    }

    public String getCheckInDayRequired() {
        return CheckInDayRequired;
    }

    public void setCheckInDayRequired(String CheckInDayRequired) {
        this.CheckInDayRequired = CheckInDayRequired;
    }

    public String getRack() {
        return rack;
    }

    public void setRack(String rack) {
        this.rack = rack;
    }

    public String getBathroom() {
        return bathroom;
    }

    public void setBathroom(String bathroom) {
        this.bathroom = bathroom;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getTaxrate() {
        return taxrate;
    }

    public void setTaxrate(String taxrate) {
        this.taxrate = taxrate;
    }

    public String getLocationid() {
        return locationid;
    }

    public void setLocationid(String locationid) {
        this.locationid = locationid;
    }

    public String getProductid() {
        return productid;
    }

    public void setProductid(String productid) {
        this.productid = productid;
    }

    public String getQuote() {
        return quote;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public String getXsl() {
        return xsl;
    }

    public void setXsl(String xsl) {
        this.xsl = xsl;
    }

    public String getExactmatch() {
        return exactmatch;
    }

    public void setExactmatch(String exactmatch) {
        this.exactmatch = exactmatch;
    }

    public String getAlert() {
        return alert;
    }

    public void setAlert(String alert) {
        this.alert = alert;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public String getSto() {
        return sto;
    }

    public void setSto(String sto) {
        this.sto = sto;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getMinstay() {
        return minstay;
    }

    public void setMinstay(String minstay) {
        this.minstay = minstay;
    }

    public String getBedroom() {
        return bedroom;
    }

    public void setBedroom(String bedroom) {
        this.bedroom = bedroom;
    }

    public String getArrive() {
        return arrive;
    }

    public void setArrive(String arrive) {
        this.arrive = arrive;
    }

    public String getCheckin() {
        return checkin;
    }

    public void setCheckin(String checkin) {
        this.checkin = checkin;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Quotedetail[] getQuotedetail() {
        return quotedetail;
    }

    public void setQuotedetail(Quotedetail[] quotedetail) {
        this.quotedetail = quotedetail;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPictureLocation() {
        return pictureLocation;
    }

    public void setPictureLocation(String pictureLocation) {
        this.pictureLocation = pictureLocation;
    }

    public String[] getAttributes() {
        return attributes;
    }

    public void setAttributes(String[] attributes) {
        this.attributes = attributes;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getBar() {
        return bar;
    }

    public void setBar(String bar) {
        this.bar = bar;
    }

    public String getInquiryOnly() {
        return inquiryOnly;
    }

    public void setInquiryOnly(String inquiryOnly) {
        this.inquiryOnly = inquiryOnly;
    }

    public String getCheckout() {
        return checkout;
    }

    public void setCheckout(String checkout) {
        this.checkout = checkout;
    }

    @Override
    public String toString() {
        return "ClassPojo [depart = " + depart + ", deposit = " + deposit + ", guests = " + guests
                + ", CheckInDayRequired = " + CheckInDayRequired + ", rack = " + rack + ", bathroom = " + bathroom
                + ", currency = " + currency + ", taxrate = " + taxrate + ", locationid = " + locationid
                + ", productid = " + productid + ", quote = " + quote + ", quantity = " + quantity + ", longitude = "
                + longitude + ", productname = " + productname + ", xsl = " + xsl + ", exactmatch = " + exactmatch
                + ", alert = " + alert + ", managerName = " + managerName + ", sto = " + sto + ", cost = " + cost
                + ", minstay = " + minstay + ", bedroom = " + bedroom + ", arrive = " + arrive + ", checkin = "
                + checkin + ", price = " + price + ", quotedetail = " + quotedetail + ", address = " + address
                + ", pictureLocation = " + pictureLocation + ", attributes = " + attributes + ", latitude = " + latitude
                + ", bar = " + bar + ", inquiryOnly = " + inquiryOnly + ", checkout = " + checkout + "]";
    }
}