package com.realdolmen.party.util;

import com.realdolmen.party.domain.Party;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by KDAAU95 on 18/09/2014.
 */
public class UploadedFileUtil {

    private File uploadedFile;

    public UploadedFileUtil(File uploadedFile) {
        this.uploadedFile = uploadedFile;
    }

    public List<Party> fileToParties() {

        List<Party> parties = new ArrayList<>();

        BufferedReader br;
        try{
            br = new BufferedReader(new FileReader(uploadedFile));
            String line;
            int lineNo = 0;
            while ((line = br.readLine()) != null) {
                if( lineNo > 0 ) {
                    parties.add( csvLineToParty( line ));
                }
                lineNo++;
            }
            br.close();
        } catch (Exception e) {
            //todo: Actually catch this stuff
        }
        return parties;

    }

    private Party csvLineToParty( String line ) {
        //todo: String to Party
        line = line.replaceAll("\"", "");
        String[] parts = line.split(",");
        if(parts.length == 6) {
            return new Party(parts[0], Double.parseDouble(parts[1]), new Date(), parts[3], parts[4], Boolean.parseBoolean(parts[5]));
        } else if (parts.length == 2 ) {
            return new Party(parts[0], Double.parseDouble(parts[1]));
        } else  {
            throw new IllegalArgumentException("A line should contain 6 fields, separated by a comma.");
        }
        //todo: Date from String input
    }
}
