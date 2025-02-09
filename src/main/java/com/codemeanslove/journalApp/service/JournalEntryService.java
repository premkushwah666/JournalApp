package com.codemeanslove.journalApp.service;

import com.codemeanslove.journalApp.entity.JournalEntry;
import com.codemeanslove.journalApp.entity.User;
import com.codemeanslove.journalApp.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class JournalEntryService {

    @Autowired
    private JournalEntryRepository journalEntryRepository;

    @Autowired
    UserService userService;

    @Transactional
    public void saveEntry(JournalEntry journalEntry, String userName){
        journalEntry.setDate(LocalDateTime.now());
        JournalEntry saved = journalEntryRepository.save(journalEntry);
        ///////ye ek operation (atomic) hona chahiye
        // man lo user null hua ya nhi mila to entry to save ho jayegi but user me to set hi nhi hogi
        User user = userService.findByUserName(userName);//1
        user.getJournalEntries().add(saved);//2
        //ye ho yha pr null to jaye
//        user.setUserName(null);   // user me save nhi hoga and atomicity disturb hogi
        //abi tak ye db me update nhi hua h
        userService.saveUser(user);
    }
    public void saveEntry(JournalEntry journalEntry){  //used in updating user
        journalEntry.setDate(LocalDateTime.now());
       journalEntryRepository.save(journalEntry);
    }

    public List<JournalEntry> getAll(){
        return  journalEntryRepository.findAll();
    }

    public Optional<JournalEntry> findById(ObjectId id) {
        return journalEntryRepository.findById(id);
    }

    @Transactional
    public boolean deleteById(ObjectId id, String userName) {
        boolean removed = false;
        try{
            User user = userService.findByUserName(userName);
            removed = user.getJournalEntries().removeIf(x -> x.getId().equals(id));
            if(removed){
                userService.saveUser(user);
                journalEntryRepository.deleteById(id);
            }

        } catch (Exception e) {
            System.out.println(e);
            throw new RuntimeException("an error occured during deleting an entry");
        }

        return  removed;
//        **  if ham delete nhi krege to next time jab koi entry save krege to vo list nikalege and check krega vo sari entries ko and
//        jab usko entries ka reference nhi milega ~ null milege to un entries of specifc user ko vo delete kr dega but next time me
//        (next time me consistency aa jayegi but hame current time me hi cosistency chahiye OK)
        /////////////////////////////////////by me niche vala for above line
//        List<JournalEntry> journalEntries = user.getJournalEntries();
//        for(JournalEntry journalEntry : journalEntries){
//            if(journalEntry.getId().equals(id)){
//                journalEntries.remove(journalEntry);
//            }
//        }
        /////////////////////////////////////////


    }
}
