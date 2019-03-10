Feature: Image attachments in an Email

	# I think this is how to comment in feature files?

  Scenario: User attaches an image file to an outgoing email
    Given the user is creating an email
    When the user adds an image file attachment
    Then the email displays that the image file is attached
