package com.realdolmen.party.bean;

import com.realdolmen.party.domain.Party;
import com.realdolmen.party.repositorie.PartyRepository;
import com.realdolmen.party.util.UploadedFileUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import javax.transaction.Transaction;

@ManagedBean(name = "inputBean")
@ViewScoped
public class InputBean implements Serializable {

    private static final long serialVersionUID = 9040359120893077422L;

    private Part part;
    private String statusMessage;

    @Inject
    PartyRepository partyRepository;


    public void uploadFile() throws IOException {

        // Extract file name from content-disposition header of file part
        String fileName = getFileName(part);
        System.out.println("***** fileName: " + fileName);

        String basePath = "C:" + File.separator + "temp" + File.separator;
        File outputFilePath = new File(basePath + fileName);

        // Copy uploaded file to destination path
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            inputStream = part.getInputStream();
            outputStream = new FileOutputStream(outputFilePath);

            int read = 0;
            final byte[] bytes = new byte[1024];
            while ((read = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, read);
            }
            outputStream.flush();

            statusMessage = "File upload successfull !!";
        } catch (IOException e) {
            e.printStackTrace();
            statusMessage = "File upload failed !!";
        } finally {
            if (outputStream != null) {
                outputStream.close();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }
        saveParties(outputFilePath);


        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());
        //return null;    // return to same page
    }

    private void saveParties(File outputFilePath) {
        boolean rollBack = false;
        UploadedFileUtil util = new UploadedFileUtil( outputFilePath );
        List<Party> succeeded = new ArrayList<>();
        List<Party> parties = util.fileToParties();
        for( Party party : parties ) {
            if( rollBack == false ) {
                partyRepository.persist(party);
                partyRepository.flush();
                succeeded.add( party );

            }
            if( party.getId() == null) {
                rollBack = true;
            }
        }
        if( rollBack ) {
            rollBackThisStuff(succeeded);
        }
    }

    private void rollBackThisStuff(List<Party> succeeded) {
        for( Party party : succeeded ) {
            partyRepository.merge(party);
            partyRepository.remove( party );
        }
    }

    public Part getPart() {
        return part;
    }

    public void setPart(Part part) {
        this.part = part;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }

    // Extract file name from content-disposition header of file part
    private String getFileName(Part part) {
        final String partHeader = part.getHeader("content-disposition");
        System.out.println("***** partHeader: " + partHeader);
        for (String content : part.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename")) {
                return content.substring(content.indexOf('=') + 1).trim()
                        .replace("\"", "");
            }
        }
        return null;
    }
}