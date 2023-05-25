package com.example.kyrsach.Metod;


import java.util.Date;

public class nameTable {
        private String nameFile;
        private String sizeFile;
        private Date lastOperationFile;
        private String typeFile;
        private String puth;

        public nameTable( String nameFile, String sizeFile,Date lastOperationFile,String typeFile,String puth) {
            this.nameFile = nameFile.substring(nameFile.lastIndexOf("/")+1);
            this.sizeFile = sizeFile;
            this.lastOperationFile = lastOperationFile;
            this.typeFile = typeFile;
            this.puth = puth;
        }
        public String getNameFile() {
            return nameFile;
        }

        public void setNameFile(String nameFile) {
            this.nameFile = nameFile;
        }

        public String getSizeFile() {
            return sizeFile;
        }

        public void setSizeFile(String sizeFile) {
            this.sizeFile = sizeFile;
        }

        public Date getLastOperationFile() {
            return lastOperationFile;
        }

        public void setLastOperationFile(Date lastOperationFile) {
            this.lastOperationFile = lastOperationFile;
        }
    public String getTypeFile() {
        return typeFile;
    }

    public void setTypeFile(String typeFile) {
        this.typeFile = typeFile;
    }
    public void setPuth(String puth)
    {
        this.puth = puth;
    }
    public String getPuth()
    {
        return puth;
    }
}

