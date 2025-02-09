package com.codemeanslove.journalApp.controller;

import com.codemeanslove.journalApp.entity.JournalEntryFirst;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/journalFirst")
public class JournalEntryFirstController {

    Map<Long, JournalEntryFirst> journalEntries = new HashMap<>();

    //    localhost:8080/journal/  <--  no
    //    localhost:8080/journal   <-- yes
    @GetMapping
    public List<JournalEntryFirst> getAll() {
//        journalEntries.put(1L,new JournalEntryFirst(1,"name","prem"));
        return new ArrayList<>(journalEntries.values());
    }

//    {
//        "id": 1,
//            "content": "name",
//            "title": "prem"
//    }
    @PostMapping
    public boolean createEntry(@RequestBody JournalEntryFirst journalEntry) {
        journalEntries.put(journalEntry.getId(), journalEntry);
        return true;
    }

    @PostMapping("/add-all")
    public boolean createEntries(@RequestBody List<JournalEntryFirst> journalEntries) {
        for (JournalEntryFirst journalEntry : journalEntries) {
            this.journalEntries.put(journalEntry.getId(), journalEntry);  //<-- this is vip bcoz of local
        }
//        Iterator<JournalEntryFirst> iterator = journalEntries.iterator();
//        while(iterator.hasNext()){
//            JournalEntryFirst journalEntry = iterator.next();
//            this.journalEntries.put(journalEntry.getId(), journalEntry);
//        }
        return true;
    }

    //    localhost:8080/journal/4
    @GetMapping("/{id}")
    public JournalEntryFirst getJournalEntryById(@PathVariable("id") Long id) {
        return journalEntries.get(id);
    }

//    localhost:8080/journal/id/4
//    @GetMapping("/id/{mid}")
//    public JournalEntryFirst getJournalEntryById1(@PathVariable("mid")Long id){
//        return journalEntries.get(id);
//    }

    @DeleteMapping("/{id}")
    public boolean deleteJournalEntryByid(@PathVariable("id") Long id) {
        JournalEntryFirst remove = journalEntries.remove(id);
        return remove != null;
    }

    @PutMapping("/{id}")
    public  JournalEntryFirst updateEntry(@PathVariable("id")Long id,@RequestBody JournalEntryFirst journalEntry){
        return journalEntries.put(id,journalEntry);
    }

}//closing of class


//
//[
//    {
//        "id": 4,
//            "content": "name",
//            "title": "shiv"
//    },
//    {
//        "id": 1,
//            "content": "name",
//            "title": "prem"
//    },
//    {
//        "id": 2,
//            "content": "name",
//            "title": "raja"
//    },
//    {
//        "id": 3,
//            "content": "name",
//            "title": "jaya"
//    }
//]
//







