package org.example;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Random;

public class Publisher {
    private int idPublisher;
    private String PublisherName;
    private String Country;

    public Publisher(@JsonProperty("idpublisher") int idPublisher, @JsonProperty("publishername") String publishername, @JsonProperty("country") String country) {
        this.idPublisher = idPublisher;
        this.PublisherName = publishername;
        this.Country = country;
    }

    public Publisher(int idPublisher) {
        String[] PublisherNames = {
                "Wal-Mart Stores",
                "Exxon Mobil",
                "Chevron",
                "Berkshire Hathaway",
                "Apple",
                "Phillips 66",
                "General Motors",
                "Ford Motor",
                "General Electric",
                "Valero Energy",
                "AT&T",
                "CVS Caremark",
                "Fannie Mae",
                "UnitedHealth Group",
                "McKesson",
                "Verizon Communications",
                "Hewlett-Packard",
                "J.P. Morgan Chase & Co.",
                "Costco Wholesale",
                "Express Scripts Holding",
                "Bank of America",
                "Cardinal Health",
                "International Business Machines",
                "Kroger",
                "Marathon Petroleum",
                "Citigroup",
                "Archer Daniels Midland",
                "AmerisourceBergen",
                "Wells Fargo",
                "Boeing",
                "Procter & Gamble",
                "Freddie Mac",
                "Home Depot",
                "Microsoft",
                "Amazon.com",
                "Target",
                "Walgreen Co.",
                "WellPoint",
                "Johnson & Johnson",
                "American International Group",
                "State Farm Insurance Cos.",
                "MetLife",
                "PepsiCo",
                "Comcast",
                "United Technologies",
                "Google",
                "ConocoPhillips",
                "Dow Chemical",
                "Caterpillar",
                "United Parcel Service",
                "Pfizer",
                "Lowe’s Companies",
                "Intel Corporation",
                "Energy Transfer Equity, L.P.",
                "Cisco Systems, Inc.",
                "Enterprise Products Partners L.P.",
                "Aetna Inc.",
                "The Coca-Cola Publisher",
                "Lockheed Martin Corporation",
                "Best Buy Co., Inc.",
                "The Walt Disney Publisher"
        };
        String[] Countries = {
                "UAE",
                "Poland",
                "Turkey",
                "Afghanistan",
                "Albania",
                "Algeria",
                "Andorra",
                "Angola",
                "Antigua and Barbuda",
                "Argentina",
                "Armenia",
                "Aruba",
                "Australia",
                "Austria",
                "Azerbaijan",
                "Bahamas",
                "Bahrain",
                "Bangladesh",
                "Barbados",
                "Belarus",
                "Belgium",
                "Belize",
                "Bolivia",
                "Bosnia and Herzegovina",
                "Botswana",
                "Brazil",
                "Brunei",
                "Bulgaria",
                "Cambodia",
                "Cameroon",
                "Canada",
                "Cayman Islands",
                "Chile",
                "China",
                "Colombia",
                "Congo",
                "Costa Rica",
                "Croatia",
                "Cuba",
                "Cyprus",
                "Czech Republic",
                "Denmark",
                "Dominican Republic",
                "Ecuador",
                "Egypt",
                "El Salvador",
                "Estonia",
                "Faroe Islands",
                "New Caledonia",
                "New Zealand"};
        this.idPublisher = idPublisher;
        this.PublisherName = PublisherNames[new Random().nextInt(PublisherNames.length)];
        this.Country = Countries[new Random().nextInt(Countries.length)];
    }

    public int getIdPublisher() {
        return idPublisher;
    }

    public void setIdPublisher(int idPublisher) {
        this.idPublisher = idPublisher;
    }

    public String getPublisherName() {
        return PublisherName;
    }

    public void setPublisherName(String PublisherName) {
        PublisherName = PublisherName;
    }

    public String getcountry() {
        return Country;
    }

    public void setcountry(String country) {
        country = country;
    }
}
