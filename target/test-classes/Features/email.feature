Feature: Image attachments in an Email

	# I think this is how to comment in feature files?

# Normal Flow
  Scenario Outline: User attaches an image file to an outgoing email to a recipient
  
    Given the user is logged into Gmail
    And that the user selects the compose button
    When the user adds the "<email>" of the recipient
    And adds a message title
    And attaches an "<imagefile>" to the email
    And the user hits the send button
    Then the email with the image will be succesfully sent to the correct recipient
    
   	Examples:
    	| email 					| imagefile |
    	| sleitman17@gmail.com| img1.jpg 	|
    	| bobby@gmail.com 		| img2.jpg 	|
    	|	steve@gmail.com 		| img3.jpg 	|
    	| chris@gmail.com 		| img4.jpg 	|
    	| nathan@gmail.com 		| img5.jpg 	|
    
    
# Alternate Flow
	Scenario Outline: User attaches an image file to an outgoing email with two recipients
	
		Given the user is logged into Gmail
    And that the user selects the compose button
    When the user adds a recipient
    And the user adds another recipient
    And adds a message title
    And attaches an image file to the email
    And the user hits the send button
    Then the email with the image will be succesfully sent to the correct recipients
    
    
    
    
    
# Error Flow
	Scenario Outline: User attaches an image file to an outgoing email with an invalid recipient email address
	
	  Given the user is logged into Gmail
    And that the user selects the compose button
    When the user adds an invalid recipient email
    And adds a message title
    And attaches an image file to the email
    And the user hits the send button
    Then the email with the image will not be correctly sent
    