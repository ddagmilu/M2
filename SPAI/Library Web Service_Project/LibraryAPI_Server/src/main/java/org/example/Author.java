package org.example;

import java.awt.*;
import java.util.Calendar;
import java.util.Random;

public class Author {
    private int idAuthor;
    private String FirstName;
    private String LastName;
    private int BirthYear;
    private String Nationality;
    private String Languages;
    //private Image AuthorImage;

    public Author(int idAuthor, String firstName, String lastName, int birthYear, String nationality, String languages){ //Image authorimage) {
        this.idAuthor = idAuthor;
        this.FirstName = firstName;
        this.LastName = lastName;
        this.BirthYear = birthYear;
        this.Nationality = nationality;
        this.Languages = languages;
        //this.AuthorImage = authorimage;
    }

    public Author(int idAuthor, int idBook) {
        this.idAuthor = idAuthor;
        String[] FNames = {
                "William",
                "Jane",
                "Charles",
                "Mark",
                "Leo",
                "Fyodor",
                "George",
                "J.K.",
                "Ernest",
                "Virginia",
                "Agatha",
                "Herman",
                "Emily",
                "Arthur",
                "Gabriel",
                "Harper",
                "Oscar",
                "Toni",
                "Thomas",
                "Margaret",
                "Jules",
                "Charlotte",
                "James",
                "Homer",
                "Albert",
                "Aldous",
                "Miguel",
                "Mary",
                "H.G.",
                "Edgar",
                "Franz",
                "Dante",
                "Virginia",
                "Gillian",
                "Maya",
                "Kurt",
                "Rudyard",
                "Milan",
                "T.S.",
                "Chinua",
                "Gabriel",
                "Albert",
                "Haruki",
                "Octavia",
                "Isabel",
                "Hermann",
                "Sylvia",
                "Ken",
                "Murasaki",
                "Zora",
                "Fyodor",
                "Joseph",
                "Rainer",
                "Jorge",
                "Italo",
                "Harper",
                "Ursula",
                "Leo",
                "Naguib",
                "Ayn",
                "Wole",
                "Günter",
                "Ngũgĩ",
                "Clarice",
                "Marguerite",
                "Ralph",
                "Jhumpa",
                "J.M.",
                "Don",
                "Nikos",
                "Alexandre",
                "Junot",
                "Jorge Luis",
                "R.K.",
                "Zadie",
                "Orhan",
                "Marianne",
                "Yukio",
                "Clarice",
                "David",
                "Jun'ichirō",
                "Jack",
                "Jean-Paul",
                "Haruki",
                "Albert",
                "Boris",
                "Joyce",
                "Wislawa",
                "Yevgeny",
                "Ernest",
                "Morad",
                "Mustapha",
                "Said",
                "Khalil",
                "Naguib",
                "Nizar",
                "Ghada",
                "Hanan",
                "Adonis",
                "Ahlam",
                "Huda",
                "Tawfiq",
                "Leila"};
        String[] LNames = {
                "Christie",
                "Melville",
                "Bronte",
                "Conan Doyle",
                "García Márquez",
                "Lee",
                "Wilde",
                "Morrison",
                "Hardy",
                "Atwood",
                "Verne",
                "Bronte",
                "Joyce",
                "Homer",
                "Huxley",
                "Cervantes",
                "Shelley",
                "Wells",
                "Poe",
                "Shakespeare",
                "Austen",
                "Dickens",
                "Twain",
                "Tolstoy",
                "Dostoevsky",
                "Orwell",
                "Rowling",
                "Hemingway",
                "Woolf",
                "Christie",
                "Melville",
                "Bronte",
                "Conan Doyle",
                "García Márquez",
                "Morrison",
                "Hardy",
                "Atwood",
                "Kafka",
                "Marquez",
                "Plath",
                "Murakami",
                "Borges",
                "Dumas",
                "Poe",
                "Tolkien",
                "Camus",
                "Nabokov",
                "Coelho",
                "Faulkner",
                "Hemingway",
                "Dickinson",
                "Woolf",
                "Eliot",
                "Huxley",
                "Mann",
                "Chaucer",
                "Steinbeck",
                "Hesse",
                "Whitman",
                "Kipling",
                "Tolstoy",
                "Fitzgerald",
                "Dickens",
                "Dostoevsky",
                "Hemingway",
                "Morrison",
                "Goethe",
                "Schiller",
                "Grass",
                "Brecht",
                "Hesse",
                "Nietzsche",
                "Rilke",
                "Fontane",
                "Heine",
                "Lessing",
                "Büchner",
                "Kleist",
                "Hoffmann",
                "Celan",
                "Storm",
                "Walser",
                "Canetti",
                "Grass",
                "Hugo",
                "Zola",
                "Voltaire",
                "Proust",
                "Flaubert",
                "Balzac",
                "Sartre",
                "Maupassant",
                "Rimbaud",
                "Verne",
                "de Beauvoir",
                "Gide",
                "Stendhal",
                "Montaigne",
                "Molière",
                "Giraudoux",
                "Rousseau",
                "Sade",
                "Gani",
                "Sufal"
        };
        String[] LNationality = {
                "United States",
                "Canada",
                "United Kingdom",
                "Germany",
                "France",
                "Italy",
                "Spain",
                "China",
                "Japan",
                "India",
                "Australia",
                "Brazil",
                "Mexico",
                "South Korea",
                "Russia",
                "South Africa",
                "Argentina",
                "Turkey",
                "Saudi Arabia",
                "Egypt",
                "Algeria"
        };
        String[] LLanguages = {
                "English", "Spanish", "French", "German", "Mandarin Chinese",
                "Hindi", "Arabic", "Portuguese", "Russian",
                "Japanese", "Javanese", "Tamil", "Turkish", "Tamazight", "Korean", "Urdu", "Italian",
                "Vietnamese", "Persian", "Polish", "Ukrainian",
                "Pashto", "Xiang Chinese", "Malayalam", "Uzbek",
                "Amharic", "Fula", "Romanian", "Oromo", "Kurdish", "Igbo",
                "Azerbaijani", "Awadhi", "Norvetchek", "Irish", "Cebuano", "Dutch"};

        this.FirstName = FNames[new Random().nextInt(FNames.length)];
        this.LastName = LNames[new Random().nextInt(LNames.length)];
        this.BirthYear = Calendar.getInstance().get(Calendar.YEAR) - new Random().nextInt(50);
        this.Nationality = LNationality[new Random().nextInt(LNationality.length)];
        this.Languages = LLanguages[new Random().nextInt(LLanguages.length)];
    }

    public void setIdAuthor(int idAuthor) {
        this.idAuthor = idAuthor;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public int getBirthYear() {
        return BirthYear;
    }

    public void setBirthYear(int birthYear) {
        BirthYear = birthYear;
    }

    public Integer getIdAuthor() {
        return this.idAuthor;
    }

    public String getNationality() {
        return Nationality;
    }

    public void setNationality(String nationality) {
        Nationality = nationality;
    }

    public String getLanguages() {
        return Languages;
    }

    public void setLanguages(String languages) {
        Languages = languages;
    }
}
