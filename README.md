# categorize

Small Java code to move files from one directory to another directory. Specify paths in the global variables.

This program currently checks only for files in the specified source path folder that are alphanumerical, can be adjusted accordingly in condition on line 26.
Example source file could be "sample-123abc". If target folder specified in path contains a folder with the substring "sample" in it, it will move this file into that folder.
The regex is set to "-" as of current, but can also be adjusted accordingly.

Run/debug and it'll do its job. Might update for more features later. Mainly just for self-use though.
