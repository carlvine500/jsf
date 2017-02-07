package com.ipd.fastjson.parser.deserializer;

import java.lang.reflect.Type;

import com.ipd.fastjson.parser.DefaultJSONParser;
import com.ipd.fastjson.JSONException;
import com.ipd.fastjson.parser.DefaultJSONParser;
import com.ipd.fastjson.parser.JSONLexer;
import com.ipd.fastjson.parser.JSONToken;
import com.ipd.fastjson.util.TypeUtils;

public class ClassDerializer implements ObjectDeserializer {

    public final static ClassDerializer instance = new ClassDerializer();

    public ClassDerializer(){
    }

    @SuppressWarnings("unchecked")
    public <T> T deserialze(DefaultJSONParser parser, Type type, Object fieldName) {
        JSONLexer lexer = parser.getLexer();
        
        if (lexer.token() == JSONToken.NULL) {
            lexer.nextToken();
            return null;
        }
        
        if (lexer.token() != JSONToken.LITERAL_STRING) {
            throw new JSONException("expect className");
        }
        String className = lexer.stringVal();
        lexer.nextToken(JSONToken.COMMA);

        return (T) TypeUtils.loadClass(className);
    }

    public int getFastMatchToken() {
        return JSONToken.LITERAL_STRING;
    }

}
