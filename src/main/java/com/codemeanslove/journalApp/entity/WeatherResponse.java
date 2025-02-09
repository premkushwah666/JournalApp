//package com.codemeanslove.journalApp.entity;
//
//import com.fasterxml.jackson.annotation.JsonProperty;
//import lombok.Getter;
//import lombok.Setter;
//
//import java.util.List;
//
//@Getter
//@Setter
//public class WeatherResponse {
//
//    public Main main;
//    public List<Weather> weather;
//
//    @Getter
//    @Setter
//    public class Main{
//        public double temp;
////        @JsonProperty("feels_like")
////        public double feelsLike;
//    }
//
//    @Getter
//    @Setter
//    public class Weather{
////        public String main;
//        public String description;
////        public String icon;
//    }
//
//
//}

package com.codemeanslove.journalApp.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class WeatherResponse {

    public Main main;
    public List<Weather> weather; // Change to List

    @Getter
    @Setter
    public static class Main { // Make static
        public double temp;

        @JsonProperty("feels_like")
        public double feelsLike;
    }

    @Getter
    @Setter
    public static class Weather { // Make static
        public String main;
        public String description;
        public String icon;
    }
}
