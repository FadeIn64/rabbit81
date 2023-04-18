package com.rabbit.src.userControllService;

import com.rabbit.src.Adapter;
import org.springframework.stereotype.Component;

@Component
public class TokensAdapter implements Adapter<String, String> {

    @Override
    public String get(String id) {
        return UsersTokensDB.tokens.get(UsersTokensDB.tokens.indexOf(id));
    }

    @Override
    public void put(String object) {
        UsersTokensDB.tokens.add(object);
    }

    @Override
    public boolean contentsObj(String id) {
        return UsersTokensDB.tokens.contains(id);
    }

    @Override
    public void deleteObj(String id) {
        UsersTokensDB.tokens.remove(id);
    }
}
