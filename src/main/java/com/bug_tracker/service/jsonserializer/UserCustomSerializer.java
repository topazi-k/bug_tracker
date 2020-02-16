package com.bug_tracker.service.jsonserializer;

import java.io.IOException;

import com.bug_tracker.model.User;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;


public class UserCustomSerializer extends StdSerializer<User> {
    
    private static final long serialVersionUID = 1L;

    public UserCustomSerializer() {
        this(null);
    }
    
    public UserCustomSerializer(Class<User> p) {
        super(p);
    }

    @Override
    public void serialize(User value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeStartObject();
        gen.writeNumberField("id", value.getId());
        gen.writeStringField("firstName", value.getFirstName());
        gen.writeStringField("lastName", value.getLastName());
        gen.writeStringField("role", value.getRole().toString());
        gen.writeEndObject(); 
    }

}
