import { Component, OnInit } from '@angular/core';
import { MediaFile } from 'src/app/models/mediafile.model';
import { GymService } from 'src/app/services/gym.service';

@Component({
  selector: 'app-gallery',
  templateUrl: './gallery.component.html',
  styleUrls: ['./gallery.component.css'],
})
export class GalleryComponent implements OnInit {

  constructor(private gymService: GymService) { }

  ngOnInit(): void {
    this.getMediaFileList();
  }

  mediaFiles: MediaFile[] = [
    {
      mediaId: '1',
      mediaName: 'Image 1',
      mediaCategory: 'Image',
      mediaUrl: 'https://images.unsplash.com/photo-1576678927484-cc907957088c?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1374&q=80',
    },
    {
      mediaId: '2',
      mediaName: 'Image 2',
      mediaCategory: 'Image',
      mediaUrl: 'https://images.unsplash.com/photo-1540497077202-7c8a3999166f?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1470&q=80',
    },
  ];

  selectedMediaFile: MediaFile | null = null;
  formMode = false;
  updateMode = false;
  updateMediaId!: string;
  newMediaName!: string;
  newMediaCategory!: string;

  selectedImageFile: File | null = null;

  showDetails(mediaFile: MediaFile) {
    this.selectedMediaFile = mediaFile;
  }

  hideDetails() {
    this.selectedMediaFile = null;
  }

  updateMediaFileForm(mediaFile: MediaFile) {
    this.formMode = true;
    this.updateMode = true;
    this.newMediaName = mediaFile.mediaName;
    this.newMediaCategory = mediaFile.mediaCategory;
    this.selectedImageFile = null;
    this.updateMediaId = mediaFile.mediaId;
  }

  addMediaFileForm() {
    this.formMode = true;
    this.updateMode = false;
    this.newMediaName = '';
    this.newMediaCategory = '';
    this.selectedImageFile = null;
  }

  closeForm() {
    this.formMode = false;
    this.updateMode = false;
  }

  private getMediaFileList() {
    this.gymService.getMediaList().subscribe((data) => {
      console.log('Media File List:', data);
      this.mediaFiles = data;
      if (data == null || data.length == 0) {
        this.addMediaFileForm();
      } else {
        this.selectedMediaFile = this.mediaFiles[0];
      }
    });
  }

  createMediaFile() {
    if (this.selectedImageFile && this.newMediaName && this.newMediaCategory) {
      const mediaFile = new FormData();
      mediaFile.append('mediaName', this.newMediaName);
      mediaFile.append('mediaCategory', this.newMediaCategory);
      mediaFile.append('media', this.selectedImageFile);

      this.gymService.addAMedia(mediaFile).subscribe((data) => {
        console.log('Media File Added:', data);
        this.getMediaFileList();
        this.closeForm();
      });
    }
  }

  updateMediaFile() {
      const mediaFile = new FormData();
      mediaFile.append('mediaName', this.newMediaName);
      mediaFile.append('mediaCategory', this.newMediaCategory);
      if (this.selectedImageFile) {
        mediaFile.append('media', this.selectedImageFile);
      }

      this.gymService.updateMedia(this.updateMediaId, mediaFile).subscribe((data) => {
        console.log('Media File Updated:', data);
        this.getMediaFileList();
        this.closeForm();
      });
  }

  deleteMediaFile(mediaId: string) {
    if (this.selectedMediaFile && confirm("Are you sure?")) {
      this.gymService.deleteMedia(mediaId).subscribe(
        (data) => {
          console.log('Media File is Deleted successfully');
          this.getMediaFileList();
        },
        (error) => {
          console.log('Error', error);
        }
      );
    }
  }

  onImageSelect(event: any) {
    const fileInput = event.target as HTMLInputElement;
    if (fileInput.files && fileInput.files.length > 0) {
      this.selectedImageFile = fileInput.files[0];
    }
  }
}
