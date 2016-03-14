package com.ningso.libcurl.params;

/**
 * Created by NingSo on 16/3/12.下午6:34
 *
 * @author: NingSo
 */
public class MultiPart {

    private final String name;
    private final String filename;
    private final String contentType;
    private final byte[] content;

    public MultiPart(String name, String filename, String contentType, byte[] content) {
        super();
        this.name = name;
        this.filename = filename;
        this.contentType = contentType;
        this.content = content;
    }

    public String getName() {
        return name;
    }


    // may be null
    public String getFilename() {
        return filename;
    }

    //may be null
    public String getContentType() {
        return contentType;
    }

    public byte[] getContent() {
        return content;
    }
}
