### Quality Policy
> Describe your Quality Policy in detail for this Sprint (remember what I ask you to do when I talk about the "In your Project" part in the lectures and what is mentioned after each assignment (in due course you will need to fill out all of them, check which ones are needed for each Deliverable). You should keep adding things to this file and adjusting your policy as you go.

**GitHub Workflow** (due Feb 2nd)
> For every user story, a new branch of master must be created with name US#XX where XX is the user story it applies to
> All tasks will have their own branch of US#XX with name Task#XX. If tasks are small, they can be grouped in sets of two or three. Think of a ‘task’ branch as something requiring 2-3 hours of work.
> When a task is ready for testing, the person responsible will make a pull request to the relevant US#XX branch for that task(storyless will have a branch ‘storyless’)
> Approval of merge will hinge on informal review of task
> GitMaster is the only one who can confirm task merge into US#XX
> GitMaster is the only one who can confirm merge task into US#XX post-review.
> Commits should only be made when substantial progress is made on a task or on task testing. Commit messages should be in the following format:
> US#XX | Task#XX | <Descriptive message here>
> When a user story is complete, a pull request to dev will be made   
> Approval of merge will hinge on formal review of user story
> GitMaster is the only one who can confirm US#XX merge into dev

**Unit Tests Blackbox** (due start Sprint 2)
> If the task branch is approved for merging (ie. has been reviewed), the reviewer is responsible for writing blackbox tests on the US branch
> Includes documentation defining equivalence classes
> Includes basic tests for all equivalence classes and some potential boundary values
> Blackbox tests will be reviewed with the US#XX formal review

 **Unit Tests Whitebox** (due Feb 25th)
> Whitebox tests will be developed by the owner of a task alongside the actual code:
> Tasks which belong to very high priority user stories must have edge 80% coverage
> Tasks which belong to high priority user stories must have edge 70% coverage
> Tasks which belong to average priority user stories must have edge 60% coverage
> Tasks which belong to low priority user stories must have edge 50% coverage
> Tasks which belong to very low priority user stories must have edge 25% coverage
> Whitebox tests will be reviewed with the task branch informal review.

**Code Review** (due Feb 25th)

> When a task is ready for testing, the person responsible will make a pull request to the relevant US#XX branch for that task(storyless will have a branch ‘storyless’) At this point it must be informally reviewed by someone before the merge can be completed. These informal reviews should take ~5 minutes.
> If it is ‘documentation’, an informal review/critique & summary of the content will suffice. Focus should be on whether the deliverable ‘completes’ the task and whether it's substantial/readable enough to be useful for others. Say what is missing, say what is good, etc... 
> If it is ‘code’, the reviewer must test the merge locally and verify that the code fulfills the task’s requirement to the best of your understanding. If it doesn’t compile or you click stuff and something breaks then share that.
> GitMaster is not solely responsible for reviews but can do them if he/she wishes
> When a user story is complete, a pull request to dev will be made and formal review of the entire US will be done by 1-2 team members.
> Focus should be on whether US Acceptance Criteria has been met & all the other good stuff related to code reviews defined in class.
> GitMaster is not solely responsible for reviews but can do them if he/she wishes. 
> Reviewers should also determine whether implemented tests are sufficient. 


**Static Analysis**  (due start Sprint 3)
  > Your Static Analysis policy   

**Continuous Integration**  (due start Sprint 3)
  > Your Continuous Integration policy
