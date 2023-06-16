package com.Project.project.Controler;

import com.Project.project.Repository.DocumentRepo;
import com.Project.project.ServiceImp.DocumentServiceImp;
import com.Project.project.model.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class DocumentController {
    @Autowired
    private DocumentServiceImp DocService;
    @Autowired
    private DocumentRepo dorepo;

    @GetMapping("/getDocument")
    public List<Document> ListALLDocuments(){
        return this.DocService.listAllDocument();
    }

    @GetMapping("/findDocumentbyid/{DocumentID}")
    public ResponseEntity<Document> getCateByIdCat(@PathVariable("DocumentID") int DocumentID) {
        Document teach = DocService.getDocumentById(DocumentID);
        return new ResponseEntity<>(teach, HttpStatus.OK);
    }
    @PostMapping("/addDocument")
    public ResponseEntity<Document> add(@RequestBody Document Document) {
        Document newteach = DocService.addDocument(Document);
        return new ResponseEntity<>(newteach, HttpStatus.CREATED);
    }
    @DeleteMapping("/deleteDocument/{DocumentID}")
    public ResponseEntity<?> deleteDocumentBYID(@PathVariable("DocumentID") int DocumentID) {
        DocService.deleteDocument(DocumentID);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("updateDocument/{DocumentID}")
    public Document updateDocumentById(@PathVariable Long DocumentID, @RequestBody Document updatedDocument) {
        return DocService.update(DocumentID, updatedDocument);
    }
}