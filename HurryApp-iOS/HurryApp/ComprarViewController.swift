//
//  ComprarViewController.swift
//  HurryApp
//
//  Created by Emmanuel Valentín Granados López on 23/11/15.
//  Copyright © 2015 DevWorms. All rights reserved.
//

import UIKit

class ComprarViewController: UIViewController, UITableViewDelegate, UITableViewDataSource, UIDocumentPickerDelegate, UIDocumentMenuDelegate, UIDocumentInteractionControllerDelegate {
    
    @IBOutlet weak var ima: UIImageView!
    @IBOutlet weak var nameDoc: UIButton!
    
    var filePath: String! = ""
    
    override func viewDidLoad() {
        super.viewDidLoad()

        // Do any additional setup after loading the view.
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    
    @IBAction func uploadFile(sender: AnyObject) {
        
        if #available(iOS 8.0, *) {
            let documentMenu = UIDocumentMenuViewController(documentTypes: ["public.image","public.data"], inMode: UIDocumentPickerMode.Import)
            
            documentMenu.delegate = self
            
            //documentMenu.modalPresentationStyle = UIModalPresentationStyle.FullScreen
            
            self.presentViewController(documentMenu, animated: true, completion: nil)
            
        } else {
            // Fallback on earlier versions
        }
        
    }
    
    @IBAction func openFile(sender: AnyObject) {
        
        if self.filePath != "" {
            let documentInteraction =  UIDocumentInteractionController(URL: NSURL(fileURLWithPath: self.filePath) )
            
            documentInteraction.delegate = self
            
            // Preview PDF
            documentInteraction.presentPreviewAnimated(true)
            //documentInteraction.presentOpenInMenuFromRect(sender.frame, inView: self.view, animated: true)
        }
        
    }
    
    // MARK: - UIDocumentInteractionControllerDelegate
    
    func documentInteractionControllerViewControllerForPreview(controller: UIDocumentInteractionController) -> UIViewController {
        return self
    }
    
    // MARK: - UIDocumentPickerDelegate
    
    @available(iOS 8.0, *)
    func documentPicker(controller: UIDocumentPickerViewController, didPickDocumentAtURL url: NSURL) {
        if controller.documentPickerMode == UIDocumentPickerMode.Import {
            
            //var data = NSData(contentsOfFile: url.path! )
            
            self.filePath = url.path!
            var fileSize : UInt64 = 0
            
            do {
                let attr : NSDictionary? = try NSFileManager.defaultManager().attributesOfItemAtPath( self.filePath)
                
                if let _attr = attr {
                    fileSize = _attr.fileSize();
                    
                    print("fileSize: \(fileSize)")
                    
                }
                
                //print ("hola: \(attr)")
                
                //self.ima.image = UIImage(contentsOfFile: attr)
                self.nameDoc.titleLabel?.text = url.lastPathComponent!
                
                
            } catch {
                print("Error: \(error)")
            }
            
        }
    }
    
    // MARK: - UIDocumentMenuDelegate
    
    @available(iOS 8.0, *)
    func documentMenu(documentMenu: UIDocumentMenuViewController, didPickDocumentPicker documentPicker: UIDocumentPickerViewController) {
        
        documentPicker.delegate = self
        presentViewController(documentPicker, animated: true, completion: nil)
        print("HurryApp Menu")
    }
    
    // MARK: - UITableViewDelegate, UITableViewDataSource
    
    func numberOfSectionsInTableView(tableView: UITableView) -> Int {
        return 1
    }
    
    func tableView(tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return 8
    }
    
    func tableView(tableView: UITableView, cellForRowAtIndexPath indexPath: NSIndexPath) -> UITableViewCell {
        
        let stringIndex = String( indexPath.row )
        
        let cell = tableView.dequeueReusableCellWithIdentifier( stringIndex ) as UITableViewCell!
        
        return cell
    }
    

    /*
    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepareForSegue(segue: UIStoryboardSegue, sender: AnyObject?) {
        // Get the new view controller using segue.destinationViewController.
        // Pass the selected object to the new view controller.
    }
    */

}