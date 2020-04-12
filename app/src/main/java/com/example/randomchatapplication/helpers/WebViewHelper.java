package com.example.randomchatapplication.helpers;

import android.webkit.WebView;

public class WebViewHelper {

        public static final String MOVING_BORDER_TYPE = "MovingBorder";

        public static String getMovingBorderHtmlCode(float desiredWidth, float desiredHeight){
            return  "<head>\n" +
                    "    <meta charset=\"UTF-8\">\n" +
                    "    <style type=\"text/css\">\n" +
                    "           html, body, div {\n" +
                    "            margin: 0;\n" +
                    "            padding: 0;\n" +
                    "            border: 0;\n" +
                    "        }" +
                    "        polygon {\n" +
                    "            stroke: #bc1e1b;\n" +
                    "            stroke-width: 4;\n" +
                    "            stroke-dasharray: "+ desiredWidth +","+ (desiredWidth *2+desiredHeight*2- desiredWidth) +";\n" +
                    "            stroke-dashoffset: 0;\n" +
                    "            fill: none;\n" +
                    "            animation: border 4s linear infinite;\n" +
                    "        }\n" +
                    "        @keyframes border {\n" +
                    "            to {\n" +
                    "                stroke-dashoffset: -"+(desiredWidth *2+desiredHeight*2) +";\n" +
                    "            }\n" +
                    "        }\n" +
                    "\n" +
                    "\n" +
                    "    </style>\n" +
                    "</head>\n" +
                    "<body >\n" +
                    "<div>\n" +
                    "    <svg width=\""+ desiredWidth +"\" height=\""+desiredHeight +"\">\n" +
                    "        <polygon points=\"0,0 "+ (desiredWidth) +",0 "+ (desiredWidth)+","+(desiredHeight)+ " 0,"+(desiredHeight)+"\" />\n" +
                    "    </svg>\n" +
                    "</div>\n" +
                    "</body>\n" +
                    "\n" +
                    "</html>";
        }

}
