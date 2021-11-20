package com.ArdhiJmartBO.controller;
import com.ArdhiJmartBO.Algorithm;
import com.ArdhiJmartBO.dbjson.Serializable;
import com.ArdhiJmartBO.dbjson.JsonTable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Iterator;
import java.util.List;

@RestController
public interface  BasicGetController <T extends  Serializable>{

    public abstract JsonTable<T> getJsonTable();

    @GetMapping("/id")
    default T getById(@PathVariable int id){
        return Algorithm.<T>find(getJsonTable(), (e) -> e.id == id);
    }

    @GetMapping("/page")
    default List<T> getPage (@PathVariable int page, @PathVariable int pageSize){
        return (List<T>) Algorithm.<T>paginate((Iterator<T>) getJsonTable(), page, pageSize, (e) -> true);
    }

}

