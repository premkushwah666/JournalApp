package com.codemeanslove.journalApp.controller;

import com.codemeanslove.journalApp.entity.JournalEntry;
import com.codemeanslove.journalApp.entity.User;
import com.codemeanslove.journalApp.service.JournalEntryService;
import com.codemeanslove.journalApp.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {

        @Autowired
        JournalEntryService journalEntryService;

        @Autowired
        UserService userService;

        @PostMapping
        public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry journalEntry) {
            try{
                Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
                String userName = authentication.getName();
                journalEntryService.saveEntry(journalEntry,userName);
                return new ResponseEntity<JournalEntry>(HttpStatus.OK);
            } catch (Exception e) {
                System.out.println(e);
                return new ResponseEntity<JournalEntry>(HttpStatus.BAD_REQUEST);
            }
        }

//        @PostMapping("/add-all")
//        public boolean createEntry(@RequestBody List<JournalEntry> js) {
//            System.out.println("add all");
//             for(JournalEntry j : js){
//                 j.setDate(LocalDateTime.now());
//                 journalEntryService.saveEntry(j, userName);
//             }
//            return true;
//        }

        @GetMapping
        public ResponseEntity<?> getAllJournalEntriesOfUser() {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String userName = authentication.getName();
            User user = userService.findByUserName(userName);
            List<JournalEntry> all = user.getJournalEntries();
            if(all!=null && !all.isEmpty()){
                return new ResponseEntity<>(all,HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

//    @GetMapping//// esme sare aa jayege oK h bas commented h
//    public ResponseEntity<?> getAllJournalEntriesOfUser() {
//        List<JournalEntry> all = journalEntryService.getAll();
//        if(all!=null && !all.isEmpty()){
//            return new ResponseEntity<>(all,HttpStatus.OK);
//        }
//        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }


        @GetMapping("id/{id}")
        public ResponseEntity<JournalEntry> getJournalEntryById(@PathVariable("id") ObjectId id) {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String userName = authentication.getName();
            User user = userService.findByUserName(userName);
            //kya ye usi user ki journal entry h
            List<JournalEntry> list = user.getJournalEntries().stream().filter(x -> x.getId().equals(id)).collect(Collectors.toList());
            if(!list.isEmpty()){
                Optional<JournalEntry> journalEntry1 = journalEntryService.findById(id);
                if(journalEntry1.isPresent()){
                    return new ResponseEntity<JournalEntry>(journalEntry1.get(), HttpStatus.OK);
                }
            }

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        @DeleteMapping("id/{myId}")
            public ResponseEntity<?> deleteJournalEntryByid(@PathVariable ObjectId myId) {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String userName = authentication.getName();


            boolean removed = journalEntryService.deleteById(myId,userName);
            if(removed){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            else{
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

        }

        @PutMapping("id/{id}")
        public ResponseEntity<?> updateEntry(
                @PathVariable("id")ObjectId id,
                @RequestBody JournalEntry newEntry
        ){
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String userName = authentication.getName();User user = userService.findByUserName(userName);
            //kya ye usi user ki journal entry h
            List<JournalEntry> list = user.getJournalEntries().stream().filter(x -> x.getId().equals(id)).collect(Collectors.toList());
            if(!list.isEmpty()){
                Optional<JournalEntry> journalEntry1 = journalEntryService.findById(id);
                if(journalEntry1.isPresent()){
                    JournalEntry old = journalEntry1.get();
                    old.setTitle( newEntry.getTitle()!=null && !newEntry.getTitle().equals("") ? newEntry.getTitle() : old.getTitle() );
                    old.setContent( newEntry.getContent()!=null && !newEntry.getContent() .equals("") ? newEntry.getContent() : old.getContent() );
                    journalEntryService.saveEntry(old);
                    return new ResponseEntity<>(old,HttpStatus.OK);
                }
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

}//closing of class

