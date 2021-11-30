package com.interview.pwc.countryrouter.input;

import java.util.HashMap;
import java.util.List;

public class Country {
    HashMap<String, Object> name;
    String cca3;
    Boolean landlocked;
    List<String> borders;

    public Country(HashMap<String, Object> name, String cca3, Boolean landlocked, List<String> borders) {
        this.name = name;
        this.cca3 = cca3;
        this.landlocked = landlocked;
        this.borders = borders;
    }

    public Country() {
    }

    public HashMap<String, Object> getName() {
        return name;
    }

    public void setName(HashMap<String, Object> name) {
        this.name = name;
    }

    public String getCca3() {
        return cca3;
    }

    public void setCca3(String cca3) {
        this.cca3 = cca3;
    }

    public Boolean getLandlocked() {
        return landlocked;
    }

    public void setLandlocked(Boolean landlocked) {
        this.landlocked = landlocked;
    }

    public List<String> getBorders() {
        return borders;
    }

    public void setBorders(List<String> borders) {
        this.borders = borders;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((cca3 == null) ? 0 : cca3.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Country other = (Country) obj;
        if (cca3 == null) {
            if (other.cca3 != null)
                return false;
        } else if (!cca3.equals(other.cca3))
            return false;
        return true;
    }

}
