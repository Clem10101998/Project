package com.example.Clemproject;

import java.util.List;

public class RestApiResponse {
    //private Integer count;
        //private String next;
        private List<Donnees> Countries;
        /*public Integer getCount() { return count;}*/
        /*public String getNext() { return next;}*/
        /* public List<Donnees> getResults(){ return results;  }*/
        public List<Donnees> getCountries(){
            return Countries;
        }

    }
