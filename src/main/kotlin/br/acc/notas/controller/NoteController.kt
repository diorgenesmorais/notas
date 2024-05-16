package br.acc.notas.controller

import br.acc.notas.model.Note
import br.acc.notas.repository.NoteRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("notes")
class NoteController {

    @Autowired
    lateinit var noteRepository: NoteRepository

    @GetMapping
    fun list(): List<Note> {
        return noteRepository.findAll().toList()
    }

    @PostMapping
    fun add(@RequestBody note: Note): ResponseEntity<Note> {
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(noteRepository.save(note))
    }

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody note: Note): ResponseEntity<Note> {
        val existingNote = noteRepository.findById(id)
        return if (existingNote.isPresent) {
            val updatedNote = existingNote.get().copy(
                title = note.title,
                description = note.description
            )
            ResponseEntity.ok(noteRepository.save(updatedNote))
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long): ResponseEntity<Void> {
        val existingNote = noteRepository.findById(id)
        return if (existingNote.isPresent) {
            noteRepository.deleteById(id)
            ResponseEntity(HttpStatus.NO_CONTENT)
        } else {
            ResponseEntity.notFound().build()
        }
    }
}