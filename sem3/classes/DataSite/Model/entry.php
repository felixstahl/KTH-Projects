<?php
namespace DataSite\Model;

//Holds one comment in the comment section.
class entry {
    private $user_name;
    private $message;
    private $timestamp;
    private $deleted;
	
	//Contructs a new instance representing one comment in the commentsection
    public function __construct($user_name, $message) {
        $this->user_name = $user_name;
        $this->message = $message;
        $this->timestamp = time();
        $this->deleted = false;
    }
    //returns the author's nick name as a string.
    public function getUsername() {
        return $this->user_name;
    }
    //return the comment as a string.
    public function getMessage() {
        return $this->message;
    }
    //return the time (on the server) as an int when this entry was created.
    public function getTimestamp() {
        return $this->timestamp;
    }
    //return true if the entry has been deleted.
    public function isDeleted() {
        return $this->deleted;
    }
    //this->deleted is set to true if the entry shall be deleted.
    public function setDeleted($deleted) {
        $this->deleted = $deleted;
    }
}
?>
