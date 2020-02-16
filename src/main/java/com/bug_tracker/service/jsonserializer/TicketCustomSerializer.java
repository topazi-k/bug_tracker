package com.bug_tracker.service.jsonserializer;

import java.io.IOException;

import com.bug_tracker.model.Ticket;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

public class TicketCustomSerializer extends StdSerializer<Ticket>{
    private static final long serialVersionUID = 1L;

    public TicketCustomSerializer() {
        this(null);
    }
    
    public TicketCustomSerializer(Class<Ticket> p) {
        super(p);
    }

    @Override
    public void serialize(Ticket value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeStartObject();
        gen.writeNumberField("id", value.getId());
        gen.writeStringField("title", value.getTitle());
        gen.writeStringField("fullDescription", value.getFullDesctiption());
        gen.writeEndObject();
        
    }
}
