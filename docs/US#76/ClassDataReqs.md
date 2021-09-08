### ***US\#76|Task\#99|Determine exactly what data should be held by each major component of the model*** ###

**This document defines the data that must be held or derivable from data held for each major component of the Taiga King model.  
It builds off of the following sources:**  
- 'Topics' Document on Canvas  
- 'UICoreRequirements' in docs/US#19  
- 'TKMemUIComparison' in docs/US#19  

**For each major structure of the model base and derived subsections are defined:**  
- The Base subsection of each class indicates data which should be attainable directly from the Taiga API.  
- The Derived subsection of each class indicates data which will likely have to be derived from base data.  

## **Project** ##  
*Base:*  
- Name  
- Description  
- ID#

*Derived:*  
- Timeline (list of **Event**)  
- Collaborators (list of **Collaborator**)  
- Sprints (list of **Sprint**)  
- \# of Sprints  
	
## **Sprint** ##  
*Base:*  
- Name  
- Start Date  
- End Date  
- \# of User Stories  
- Total Points Possible  
- Total Points Earned  
- Open Tasks  
- Closed Tasks  
- % Completion  
- ID#
  
*Derived:*  
- Finished (past end date?)  
- Stories (list of **UserStory**)  
- Tasks completed per day (average)  
- Burndown (chart displaying point progression over time)  
- Rating (out of 10; derived based on team's performance relevant to ideal burndown & incorrect event #)  
- \# of Incorrect Events (incremented whenever tasks moves in the wrong direction or by the wrong person)  
- Timeline (list of **Event**) associated with this sprint's user stories  
- Collaborators (list of **Collaborator**)  

## **User Story** ##  
*Base:*  
- Title  
- Description (includes acceptance criteria)  
- Date Added to Product Backlog  
- Date Added to Sprint Backlog  
- ID#  

*Derived:*  
- Tasks (list of **Task**)  
- Collaborators (list of **Collaborator**)  
- Timeline (list of **Event**) associated with this user story  
- Completion Date (all Tasks complete)  
- In Progress Task \#  
- Waiting for Test Task \#  
- Ready Task \#  
- Complete Task \#  
- Total Task \#  

## **Task** ##  
*Base:*  
- Title  
- Description  
- Date Added to User Story  
- ID#  
- Current State (Ready, In Progress, Ready for Test, Complete, Needs Information)  
- Claimant (**Collaborator** who has taken it; null if none)  

*Derived:*  
- Time in Current State (if not Complete)  
- Timeline (list of **Event**) associated with this task  
- Completion Date (defined if state == complete)  

## **Event** ##
*Base:*  
- Task (**Task**)  
- Description  
- Date  

*Derived:*  
- Start State  
- End State  

**Note on the above:**
I'm not sure how these are represented in Taiga but, basically, this is a structure which holds data related
to state transitions. Some examples would be:  
- When a **Task** moves from Ready to In Progress, In Progress to Testing, etc...  
- When a **Task** is added to a **Userstory**  there is a 'creation' event  
- When a **Task** is deleted from a **UserStory** there is a 'deletion' event  
- etc...  
The goal is to be able to replay / analyze the timeline of a **Sprint** based on these events and make judgements based on these details. The above only supports tasks but this functionality could be easily extended to user stories.

## **Collaborator** ##
*Base:*
- Username
- First Name
- Last Name
- Icon ( whatever form the Taiga icons can be imported in for display ) 

*Derived:*
- sprints (list of **Sprint**)
- stories (list of **UserStory**)
- tasks (list of **Task**)
- events (list of **Event**)

**Note on the above:**
These sprints/stories/tasks/events would not be stored with the Collaborator -- there should just be some means of accessing an users scrumboard data.
