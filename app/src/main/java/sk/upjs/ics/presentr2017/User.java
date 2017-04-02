package sk.upjs.ics.presentr2017;

class User {
    private String login;

    public User() {
        // prazdny konstruktor
    }

    public User(String login) {
        this.login = login;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Override
    public String toString() {
        return this.login;
    }
}
